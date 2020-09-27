package com.cvilia.bubbleweather.pages.once;


import android.Manifest;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.component.PermissionRejectTipDialog;
import com.cvilia.bubbleweather.config.Constants;
import com.cvilia.bubbleweather.config.PageUrlConfig;
import com.cvilia.bubbleweather.utils.StatusUtil;
import com.tbruyelle.rxpermissions3.RxPermissions;
import com.tencent.mmkv.MMKV;

/**
 * 权限说明页 一般首次启动的时候回显示当前页面
 */
@Route(path = PageUrlConfig.PERMISSION_EXPLAIN_PAGE)
public class PermissionExplainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusUtil.hideStatusBar(this);
        setContentView(R.layout.activity_permission_explain);
        findViewById(R.id.rejectBtn).setOnClickListener(v -> showTipDialog());
        findViewById(R.id.agreeBtn).setOnClickListener(v -> requestPermissions());
    }

    /**
     * 请求权限
     */
    private void requestPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEachCombined(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(permission -> {
                    if (permission.granted) {//只要有一个权限同意都能进入
                        enterMainPage();
                    } else if (permission.shouldShowRequestPermissionRationale){//有一个失败也能进入应用
                        Toast.makeText(this, "权限获取失败", Toast.LENGTH_SHORT).show();
                    }else {

                        Toast.makeText(this, "不再询问", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 拒绝权限弹窗提示
     */
    private void showTipDialog() {
        new PermissionRejectTipDialog(this, "如果您拒绝以上权限将无法享受我们的优质服务").show();
    }

    /**
     * 进入主应用
     */
    private void enterMainPage() {
        ARouter.getInstance().inject(this);
        ARouter.getInstance().build(PageUrlConfig.MAIN_PAGE).navigation(this, new NavCallback() {
            @Override
            public void onArrival(Postcard postcard) {
                MMKV.defaultMMKV().encode(Constants.FIRST_START, false);
                finish();
            }
        });
    }
}