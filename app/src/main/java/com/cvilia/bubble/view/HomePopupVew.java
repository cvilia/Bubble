package com.cvilia.bubble.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cvilia.bubble.R;
import com.cvilia.bubble.route.PageUrlConfig;

/**
 * author: lzy
 * date: 1/28/21
 * describe：首页右上角弹出菜单
 */
public class HomePopupVew extends PopupWindow implements View.OnClickListener {
    private Context context;

    public HomePopupVew(Context context) {
        super(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.context = context;
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setOutsideTouchable(true);
        setFocusable(true);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_popup_view, null);
        TextView centerTv = view.findViewById(R.id.centerTv);
        centerTv.setOnClickListener(this);
        TextView shareTv = view.findViewById(R.id.shareTv);
        shareTv.setOnClickListener(this);
        TextView eatWhatTv = view.findViewById(R.id.eatWhatTv);
        eatWhatTv.setOnClickListener(this);
        setContentView(view);
        setAnimationStyle(R.style.PopupView);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.centerTv) {
            ARouter.getInstance().build(PageUrlConfig.CENTER_PAGE).navigation(context);
        } else if (v.getId() == R.id.shareTv) {
            ARouter.getInstance().build(PageUrlConfig.SHARE_PAGE).navigation(context);
        } else if (v.getId() == R.id.eatWhatTv) {
            ARouter.getInstance().build(PageUrlConfig.EAT_WHAT_PAGE).navigation(context);
        }
        HomePopupVew.this.dismiss();
    }
}
