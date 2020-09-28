package com.cvilia.bubbleweather.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * author: lzy
 * date: 2020/9/28
 * describe：日落
 * 实现思路，12~24点的半圆（其实就是进度条）
 * 半圆中心写日落，日落上面写时间
 */
public class SunSetView extends View {
    public SunSetView(Context context) {
        super(context);
    }

    public SunSetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SunSetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
