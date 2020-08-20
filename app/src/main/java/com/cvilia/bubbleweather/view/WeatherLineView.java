package com.cvilia.bubbleweather.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * author: lzy
 * date: 2020/8/20
 * describe：描述
 */
public class WeatherLineView extends View {

    private Context mCOntext;

    public WeatherLineView(Context context) {
        this(context,null);
    }

    public WeatherLineView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public WeatherLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
