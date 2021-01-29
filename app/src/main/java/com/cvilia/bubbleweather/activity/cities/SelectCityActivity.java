package com.cvilia.bubbleweather.activity.cities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.amap.api.location.AMapLocation;
import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.adapter.SelectCityAdapter;
import com.cvilia.bubbleweather.adapter.SingleTextAdapter;
import com.cvilia.bubbleweather.base.BaseActivity;
import com.cvilia.bubbleweather.bean.City;
import com.cvilia.bubbleweather.config.Constants;
import com.cvilia.bubbleweather.databinding.ActivitySelectCityBinding;
import com.cvilia.bubbleweather.route.PageUrlConfig;
import com.cvilia.bubbleweather.utils.DeviceUtil;
import com.cvilia.bubbleweather.utils.DisplayUtil;
import com.cvilia.bubbleweather.utils.MMKVUtil;
import com.cvilia.bubbleweather.view.MessageSingleButtonDialog;
import com.cvilia.bubbleweather.view.MessageTwoButtonDialog;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 选择城市
 */
@Route(path = PageUrlConfig.SELECT_CITY_PAGE)
public class SelectCityActivity extends BaseActivity<SelectCityPresenter> implements SelectCityContact.View,
        View.OnClickListener, TextView.OnEditorActionListener {
    private ActivitySelectCityBinding mBindings;
    private List<City> mCityInfos;
    private String currentCity = "北京市";
    private SingleTextAdapter mHotCityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void initWidget() {
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.icon_search_333333, null);
        drawable.setBounds(0, 0, DisplayUtil.dp2px(this, 16), DisplayUtil.dp2px(this, 16));
        mBindings.keywordEt.setCompoundDrawables(drawable, null, null, null);
    }

    @Override
    protected void onViewCreated() {
        StatusBarUtil.setLightMode(this);
        mPresenter.startLocate();
//        showKeyboard();
    }

    /**
     * 设置进入该页面后自动获取EditText焦点并弹出软键盘
     */
    private void showKeyboard() {
        mBindings.keywordEt.requestFocus();
        /**
         * 定时器是为了保证所有UI都加载完毕
         */
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(mBindings.keywordEt, 0);
            }
        }, 200);
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(mBindings.keywordEt, 0);
    }

    @Override
    protected View inflatRootView() {
        mBindings = ActivitySelectCityBinding.inflate(getLayoutInflater());
        return mBindings.getRoot();
    }

    @Override
    protected void initWidgetEvent() {
        mBindings.cancelTv.setOnClickListener(v -> finish());
        mBindings.keywordEt.setOnEditorActionListener(this);
        addPic2LocateTextView();
        mBindings.hotCityRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mBindings.hotCityRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int index = parent.getChildAdapterPosition(view);
                if (index != 0 && index != 1 && index != 2) {
                    outRect.top = DisplayUtil.dp2px(SelectCityActivity.this, 15);
                }
            }
        });
        List<String> hotCities = new ArrayList<>();
        hotCities.addAll(Arrays.asList(Constants.PROVINCIAL_CAPITAL));
        mHotCityAdapter = new SingleTextAdapter(hotCities, this);
        mBindings.hotCityRecyclerView.setAdapter(mHotCityAdapter);

    }

    /**
     * 向显示当前位置的TextView设置图片
     */
    private void addPic2LocateTextView() {
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.icon_locate_3333_6, null);
        drawable.setBounds(0, 0, DisplayUtil.dp2px(this, 16), DisplayUtil.dp2px(this, 16));
        mBindings.locateCityTv.setCompoundDrawables(null, null, drawable, null);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getIntentData() {

    }


    @Override
    protected SelectCityPresenter getPresenter() {
        return new SelectCityPresenter();
    }

    @Override
    public void loading() {

    }

    @Override
    public void dismissLoading() {

    }


    @Override
    public void readDbSuccess(List<City> cityList) {
        if (cityList != null) {
            this.mCityInfos = cityList;
//            SelectCityAdapter adapter = new SelectCityAdapter(R.layout.layout_city_item, cityList);
//            adapter.setOnItemClickListener(this);
//            mCityRecycler.setAdapter(adapter);
//            this.mCityInfos = cityList;
            getProvinces();
        }
    }

    @Override
    public void locateSuccess(AMapLocation location) {
        currentCity = location.getDistrict();
        mBindings.locateCityTv.setText(location.getDistrict());
    }


    @Override
    public void locateFailed() {
        mBindings.locateCityTv.setText("定位失败");
    }

    /**
     * 获取省份
     */
    private void getProvinces() {
        List<String> list = new ArrayList<>();
        for (City city : mCityInfos) {
            list.add(city.getProvinceZh());
        }

        //省份
        List<String> mProvinces = new ArrayList<>();
        for (String province : list) {
            if (!mProvinces.contains(province)) {
                mProvinces.add(province);
            }
        }
//        mBindings.provienceRecyclerView.addItemDecoration(new ProvinceDivider(this));
        SingleTextAdapter adapter = new SingleTextAdapter(mProvinces, this);
//        mBindings.provienceRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            List<City> selectedInfos = new ArrayList<>();
            for (City city : mCityInfos) {
                if (city.getProvinceZh().equals(adapter.getItem(position))) {
                    selectedInfos.add(city);
                }
            }
            getSlectedProvinceInfo(selectedInfos);
        });
    }


    /**
     * 加载选定省份的城市
     *
     * @param selectedInfos 选择的省份信息
     */
    private void getSlectedProvinceInfo(List<City> selectedInfos) {
        SelectCityAdapter adapter = new SelectCityAdapter(R.layout.layout_city_item, selectedInfos);
//        mBindings.cityRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            City city = selectedInfos.get(position);
            MMKVUtil.saveString(Constants.SELECTED_CITY, city.getCityZh());
            Intent intent = new Intent();
            intent.putExtra("cityCode", city.getId());
            intent.putExtra("cityName", city.getCityZh());
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    @Override
    public void onClick(View v) {

//        if (v.getId() == R.id.backIv) {
//            finish();
//        }
//        if (v.getId() == R.id.currentLocationRl) {
//            Intent intent = new Intent();
//            intent.putExtra("cityCode", "");
//            intent.putExtra("cityName", currentCity);
//            setResult(RESULT_OK, intent);
//            finish();
//        }

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (null != event && KeyEvent.KEYCODE_ENTER == event.getKeyCode()) {
            if (event.getAction() == KeyEvent.ACTION_UP) {
                DeviceUtil.hideSoftKeyboard(SelectCityActivity.this);
                String cityName = mBindings.keywordEt.getText().toString();
                if (TextUtils.isEmpty(cityName)) {
                    MessageSingleButtonDialog dialog = new MessageSingleButtonDialog(this, getString(R.string.search_content_nonull));
                    dialog.show();
                } else {
                    if (cityName.contains("区") ||
                            cityName.contains("县") ||
                            cityName.contains("乡") ||
                            cityName.contains("镇")) {
                        cityName = cityName.substring(0, cityName.length() - 1);
                    }
                    mPresenter.readDb(cityName);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 监听软键盘的回车按钮
     *
     * @param v
     * @param actionId
     * @param event
     * @return
     */

}