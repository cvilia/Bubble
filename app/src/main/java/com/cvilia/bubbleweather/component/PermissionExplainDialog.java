package com.cvilia.bubbleweather.component;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.adapter.PermissionExplainAdapter;
import com.cvilia.bubbleweather.listener.IPermissionAcceptClick;

import java.util.ArrayList;

/**
 * author: lzy
 * date: 2020/8/17
 * describe：启动页权限说明dialog
 */
public class PermissionExplainDialog extends Dialog implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private IPermissionAcceptClick onPermissionAcceptClick;
    private Context mContext;

    public PermissionExplainDialog(@NonNull Context context) {
        super(context,R.style.PermissionExplainDialog);
        this.mContext = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_permission_explain);
        setCanceledOnTouchOutside(false);
        init();
    }

    private void init() {
        mRecyclerView = findViewById(R.id.permissionRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        Button mConfirmBtn = findViewById(R.id.acceptPermissionBtn);
        mConfirmBtn.setOnClickListener(this);

        initData();

    }

    private void initData() {
        ArrayList<String> permissionNames = new ArrayList<>();
        permissionNames.add("定位权限：获取当前位置查询天气");
        permissionNames.add("文件权限：读取本地文件");
        permissionNames.add("相册权限：读取本地相册");
        permissionNames.add("手机权限：获取手机信息");
        PermissionExplainAdapter adapter = new PermissionExplainAdapter(R.layout.dialog_permission_item,permissionNames);
        mRecyclerView.setAdapter(adapter);
    }

    public void setOnPermissionAcceptClick(IPermissionAcceptClick onPermissionAcceptClick) {
        this.onPermissionAcceptClick = onPermissionAcceptClick;
    }

    @Override
    public void onClick(View v) {
        dismiss();
        onPermissionAcceptClick.onAccept();
    }
}
