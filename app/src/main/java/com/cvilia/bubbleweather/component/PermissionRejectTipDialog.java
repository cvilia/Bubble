package com.cvilia.bubbleweather.component;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cvilia.bubbleweather.R;

/**
 * author: lzy
 * date: 2020/8/17
 * describe：拒绝权限提示弹窗
 */
public class PermissionRejectTipDialog extends Dialog {

    private String tips;

    public PermissionRejectTipDialog(@NonNull Context context, @NonNull String tips) {
        super(context, R.style.PermissionExplainDialog);
        this.tips = tips;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_permission_explain);
        setCanceledOnTouchOutside(false);
        TextView textView = findViewById(R.id.tipTv);
        textView.setText(tips);
        findViewById(R.id.confirmBtn).setOnClickListener(v -> dismiss());
    }
}
