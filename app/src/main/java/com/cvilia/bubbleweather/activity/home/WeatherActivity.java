package com.cvilia.bubbleweather.activity.home;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.amap.api.location.AMapLocation;
import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.adapter.Day7Adapter;
import com.cvilia.bubbleweather.adapter.Hour7Adapter;
import com.cvilia.bubbleweather.base.BaseActivity;
import com.cvilia.bubbleweather.bean.Day7WeatherBean;
import com.cvilia.bubbleweather.bean.Day7WeatherBean.DataBean;
import com.cvilia.bubbleweather.config.Constants;
import com.cvilia.bubbleweather.route.PageUrlConfig;
import com.cvilia.bubbleweather.databinding.ActivityMainBinding;
import com.cvilia.bubbleweather.utils.CopyDb2Local;
import com.cvilia.bubbleweather.utils.DisplayUtil;
import com.cvilia.bubbleweather.utils.MMKVUtil;
import com.cvilia.bubbleweather.view.HomePopupVew;
import com.cvilia.bubbleweather.view.RecyclerViewDivider;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import java.io.File;
import java.util.ArrayList;

@Route(path = PageUrlConfig.MAIN_PAGE)
public class WeatherActivity extends BaseActivity<HomePagePresenter> implements HomePageContact.View, OnRefreshListener {

    private static final int REQUEST_CODE_SELECT_SITE = 0x1101;
    private static final int REQUEST_CODE_SELECT_IMG = 0x1102;
    private static final String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private ActivityMainBinding mBindings;
    private static boolean isFirstIn = true;

    private boolean selectCity = false;//是否是选择过城市
    private String cityCode;
    private String cityName;

    private HomePopupVew popupVew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalChangedReceiver.unRegister();
        mLocalChangedReceiver = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(MMKVUtil.getString(Constants.MAIN_PAGE_BG_PATH))) {
            File file = new File(MMKVUtil.getString(Constants.MAIN_PAGE_BG_PATH));
            if (!file.isFile()) {
                return;
            }
            Uri uri = Uri.fromFile(file);
            setBackgroundImg(uri);
        }
    }

    @Override
    protected void onViewCreated() {
        //自定义actionbar
//        LinearLayout.LayoutParams lp = (LinearLayoBut.LayoutParams) mBindings.actionBar.actionBarRl.getLayoutParams();
//        lp.topMargin = DisplayUtil.getStatusBarHeight(this)+10;

    }

    @Override
    protected View inflatRootView() {
        mBindings = ActivityMainBinding.inflate(getLayoutInflater());
        return mBindings.getRoot();
    }

    @Override
    protected void initWidget() {
        popupVew = new HomePopupVew(this);
        mBindings.refreshL.setPrimaryColorsId(R.color.bg_90b8d1, R.color.app_main);
    }


    @Override
    protected void initWidgetEvent() {
        mBindings.refreshL.setEnableLoadMore(false);
        if (isFirstIn) {
            isFirstIn = false;
            mBindings.refreshL.autoRefresh();
        }
        mBindings.refreshL.setOnRefreshListener(this);
        mBindings.actionBar.leftIv.setOnClickListener(view -> ARouter.getInstance().build(PageUrlConfig.CITIES_PAGE).navigation());
//        mBindings.actionBar.rightIv.setVisibility(View.INVISIBLE);
        mBindings.actionBar.rightIv.setOnClickListener(view -> {
            popupVew.showAsDropDown(view, DisplayUtil.dp2px(this, -60), DisplayUtil.dp2px(this, 10));
//            if (RxPermissionUtils.checkPermissions(this, PERMISSIONS)) {
//                selectImg();
//            } else {
//                RxPermissionUtils.requestPermissions(this, PERMISSIONS, new RxPermissionUtils.OnPermissionCallBack() {
//                    @Override
//                    public void onPermissionsGranted() {
//                        selectImg();
//                    }
//
//                    @Override
//                    public void onAtLeastOneReject(Permission permission) {
//                        Toast.makeText(mContext, "请求文件读写权限失败", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onAllRejectAndDoNotAskAgain(Permission permission) {
//                        MessageTwoButtonDialog messageTwoButtonDialog = new MessageTwoButtonDialog(mContext, "访问系统相册需要文件读写权限，是否前往系统设置授予权限？", new TwoButtonClickListener() {
//                            @Override
//                            public void onConfirm() {
//                                RxPermissionUtils.toAppSetting(mContext);
//                            }
//
//                            @Override
//                            public void onCancle() {
//                            }
//                        });
//                    }
//                });
//            }
        });


        LinearLayoutManager day7Lp = new LinearLayoutManager(this);
        day7Lp.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBindings.day7RecyclerView.setLayoutManager(day7Lp);
        mBindings.day7RecyclerView.addItemDecoration(new RecyclerViewDivider(null, this));
        LinearLayoutManager hour7Lp = new LinearLayoutManager(this);

        hour7Lp.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBindings.hourRecyclerView.setLayoutManager(hour7Lp);
        mBindings.hourRecyclerView.addItemDecoration(new RecyclerViewDivider(null, this));
    }

    private void selectImg() {
        Matisse.from(mContext)
                .choose(MimeType.of(MimeType.JPEG, MimeType.PNG, MimeType.GIF))
                .countable(true)
                .maxSelectable(1)
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.8f)
                .originalEnable(true)
                .maxOriginalSize(2)
                .imageEngine(new PicassoEngine())
                .forResult(REQUEST_CODE_SELECT_IMG);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == REQUEST_CODE_SELECT_SITE) {
            cityCode = data.getStringExtra("cityCode");
            cityName = data.getStringExtra("cityName");
            mBindings.actionBar.centerTv.setText(data.getStringExtra("cityName"));
            selectCity = true;
            mBindings.refreshL.autoRefresh();
        } else if (requestCode == REQUEST_CODE_SELECT_IMG) {
            ArrayList<Uri> imgs = (ArrayList<Uri>) Matisse.obtainResult(data);
            Uri uri = imgs.get(0);
            getRealFilePath(uri);
            setBackgroundImg(uri);
        }
    }

    private void setBackgroundImg(Uri uri) {
        try {
            Drawable drawable = Drawable.createFromStream(getContentResolver().openInputStream(uri), "abc");
            mBindings.mainBg.setBackground(drawable);
        } catch (Exception e) {
            Log.d("lizhenyu", e.toString());
        }
    }

    public void getRealFilePath(final Uri uri) {
        if (null == uri) {
            return;
        }
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = this.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        MMKVUtil.saveString(Constants.MAIN_PAGE_BG_PATH, data);
    }


    @Override
    protected void initData() {
        CopyDb2Local.copy2localdb(this);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return super.onGenericMotionEvent(event);
    }

    @Override
    protected void getIntentData() {
    }

    @Override
    protected HomePagePresenter getPresenter() {
        return new HomePagePresenter();
    }


    /**
     * 重新加载当日天气信息
     *
     * @param bean 天气实体类
     */
    private void reloadCurrentInfo(Day7WeatherBean bean) {
        DataBean todayInfo = bean.getData().get(0);
        if (todayInfo != null) {
            mBindings.tempTv.setText(todayInfo.getTem());
            mBindings.weatherDescTv.setText(todayInfo.getWea());
            mBindings.AQITv.setText(String.format("AQI：%s（%s）", todayInfo.getAir(), todayInfo.getAir_level()));
            mBindings.updateTimeTv.setText(String.format("上次更新：%s", bean.getUpdate_time().substring(11)));
        }
        reloadDay7Weather(bean);
    }

    /**
     * 加载7日天气信息
     *
     * @param bean 天气信息
     */
    private void reloadDay7Weather(Day7WeatherBean bean) {
        Day7Adapter day7Adapter = new Day7Adapter(bean, this);
        mBindings.day7RecyclerView.setAdapter(day7Adapter);

        Hour7Adapter adapter = new Hour7Adapter(bean.getData().get(0).getHours(), this);
        mBindings.hourRecyclerView.setAdapter(adapter);

    }

    @Override
    public void showRequestSuccess(Day7WeatherBean bean) {
        mBindings.refreshL.finishRefresh();
        if (bean != null) runOnUiThread(() -> reloadCurrentInfo(bean));
    }

    @Override
    public void showRequestFailed() {

    }

    @Override
    public void locateSuccess(AMapLocation location) {
        if (TextUtils.isEmpty(MMKVUtil.getString(Constants.SELECTED_CITY))) {
            mPresenter.requestWeatherInfo(location.getDistrict());
            mBindings.actionBar.centerTv.setText(location.getDistrict());
        } else {
            mPresenter.requestWeatherInfo(MMKVUtil.getString(Constants.SELECTED_CITY));
            mBindings.actionBar.centerTv.setText(MMKVUtil.getString(Constants.SELECTED_CITY));
        }
    }

    @Override
    public void locateFailed() {
        if (TextUtils.isEmpty(MMKVUtil.getString(Constants.SELECTED_CITY))) {
            mBindings.refreshL.finishRefresh();
            mPresenter.requestWeatherInfo("北京市");
            mBindings.actionBar.centerTv.setText("北京市");
        } else {
            mPresenter.requestWeatherInfo(MMKVUtil.getString(Constants.SELECTED_CITY));
            mBindings.actionBar.centerTv.setText(MMKVUtil.getString(Constants.SELECTED_CITY));
        }
    }


    @Override
    public void loading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if (!selectCity) {
            mPresenter.startLocate();
        }
        if (selectCity) {
            if (TextUtils.isEmpty(cityCode)) {
                mPresenter.requestWeatherInfo(cityName);
            } else {
                mPresenter.requestWeatherInfo(cityCode);
            }
        }
    }
}