package com.cvilia.bubble.activity.weather;

import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.amap.api.location.AMapLocation;
import com.cvilia.base.BaseActivity;
import com.cvilia.base.util.DeviceUtil;
import com.cvilia.bubble.R;
import com.cvilia.bubble.adapter.SearchCityAdapter;
import com.cvilia.bubble.adapter.TopCityAdapter;
import com.cvilia.bubble.config.Constants;
import com.cvilia.bubble.mvp.contact.SelectCityContact;
import com.cvilia.bubble.databinding.ActivitySelectCityBinding;
import com.cvilia.bubble.event.MessageEvent;
import com.cvilia.bubble.mvp.presenter.SelectCityPresenter;
import com.cvilia.bubble.route.PageUrlConfig;
import com.cvilia.bubble.utils.DisplayUtil;
import com.cvilia.bubble.utils.MMKVUtil;
import com.cvilia.bubble.view.MessageSingleButtonDialog;
import com.jaeger.library.StatusBarUtil;
import com.qweather.sdk.bean.geo.GeoBean;

import org.greenrobot.eventbus.EventBus;

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
    //    private List<City> mCityInfos;
    private String currentCity = "北京市";
    private TopCityAdapter mTopCityAdapter;
    private String keyword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean registerEventBus() {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
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
    protected void initView() {
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.icon_search_333333_6, null);
        drawable.setBounds(0, 0, DisplayUtil.dp2px(this, 16), DisplayUtil.dp2px(this, 16));
        mBindings.keywordEt.setCompoundDrawables(drawable, null, null, null);

        mBindings.cancelTv.setOnClickListener(v -> finish());
        mBindings.keywordEt.setOnEditorActionListener(this);
        addPic2LocateTextView();
        mBindings.hotCityRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mBindings.hotCityRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                                       @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int index = parent.getChildAdapterPosition(view);
                if (index > 2) {
                    outRect.top = DisplayUtil.dp2px(SelectCityActivity.this, 15);
                }
            }
        });
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
    protected void getData() {
        mPresenter.requestTopCity();
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
    public void searchSuccess(List<String> cities) {
        mBindings.hotCityCl.setVisibility(View.GONE);
        mBindings.searchCityRecycler.setVisibility(View.VISIBLE);
        mBindings.searchCityRecycler.setLayoutManager(new LinearLayoutManager(this));
        SearchCityAdapter adapter = new SearchCityAdapter(cities);
        adapter.setEmptyView(R.layout.layout_data_empty);
        mBindings.searchCityRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            MMKVUtil.saveString(Constants.SELECTED_CITY, keyword);
            EventBus.getDefault().post(MessageEvent.getInstance("selectCity"));
            finish();
        });
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

    @Override
    public void loadTopCity(GeoBean bean) {
        runOnUiThread(() -> {
            mTopCityAdapter = new TopCityAdapter(bean.getLocationBean(), SelectCityActivity.this);
            mBindings.hotCityRecyclerView.setAdapter(mTopCityAdapter);
            mTopCityAdapter.setOnItemClickListener((adapter, view, position) -> {
                GeoBean.LocationBean cityInfo = (GeoBean.LocationBean) adapter.getData().get(position);
                String cityName = cityInfo.getName();
                if (!TextUtils.isEmpty(cityName)) {
                    MMKVUtil.saveString(Constants.SELECTED_CITY, cityName);
                    EventBus.getDefault().post(MessageEvent.getInstance("selectCity"));
                    finish();
                }
            });
        });
    }

    @Override
    public void onClick(View v) {


    }

    /**
     * 监听软键盘的回车按钮
     *
     * @param v
     * @param actionId
     * @param event
     * @return
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (null != event && KeyEvent.KEYCODE_ENTER == event.getKeyCode()) {
            if (event.getAction() == KeyEvent.ACTION_UP) {
                DeviceUtil.hideSoftKeyboard(SelectCityActivity.this);
                keyword = mBindings.keywordEt.getText().toString();
                if (TextUtils.isEmpty(keyword)) {
                    MessageSingleButtonDialog dialog = new MessageSingleButtonDialog(this,
                            getString(R.string.search_content_nonull));
                    dialog.show();
                } else {
                    // 本地加载城市的方式废弃，通过网络获取热门城市
//                    mPresenter.readDb(keyword);
                }
            }
            return true;
        }
        return false;
    }
}