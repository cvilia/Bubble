package com.cvilia.bubble.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.cvilia.bubble.R;
import com.cvilia.bubble.databinding.DialogMessageTwoButtonBinding;
import com.cvilia.bubble.listener.TwoButtonClickListener;

/**
 * author: lzy
 * date: 1/21/21
 * describe：两个按钮的Dialog
 */
public class MessageTwoButtonDialog extends Dialog implements View.OnClickListener {

    private TwoButtonClickListener mListener;
    private String mMessage;

    private DialogMessageTwoButtonBinding mBinding;

    public MessageTwoButtonDialog(@NonNull Context context, String message, TwoButtonClickListener listener) {
        super(context, R.style.CommonDialog);
        mListener = listener;
        mMessage = message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DialogMessageTwoButtonBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        mBinding.contentTv.setText(mMessage);
        mBinding.confirmTv.setOnClickListener(this);
        mBinding.cancleTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        dismiss();
        if (v.getId() == R.id.cancleTv) {
            mListener.onCancle();
        } else {
            mListener.onConfirm();
        }

    }
}
