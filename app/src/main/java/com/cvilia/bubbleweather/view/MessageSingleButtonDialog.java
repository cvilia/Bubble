package com.cvilia.bubbleweather.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.databinding.DialogMessageSingleButtonBinding;

/**
 * author: lzy
 * date: 1/29/21
 * describe：描述
 */
public class MessageSingleButtonDialog extends Dialog {

    private String mMessage;
    private DialogMessageSingleButtonBinding mBindings;

    public MessageSingleButtonDialog(@NonNull Context context, String message) {
        super(context, R.style.CommonDialog);
        this.mMessage = message;
        mBindings = DialogMessageSingleButtonBinding.inflate(getLayoutInflater());
        setContentView(mBindings.getRoot());
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        mBindings.contentTv.setText(mMessage);
        mBindings.buttonLl.setOnClickListener(v -> dismiss());
    }
}
