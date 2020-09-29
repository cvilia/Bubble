package com.cvilia.bubbleweather.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.cvilia.bubbleweather.utils.DimenUtil;

/**
 * author: lzy
 * date: 2020/9/28
 * describe：自定义日出
 * 实现思路，0~12点的半圆（其实就是进度条）
 * 半圆中心写日出，日出上面写时间
 */
public class SunRiseView extends View {

    private String time;//日出时间
    private Paint mTimePaint, mTextPaint, mCirclePaint, mBoldPaint;
    private Context context;
    private int radius = 60;

    private RectF rectF;


    private float progress = 0;

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    public void setData(String time) {
        this.time = time;

    }

    public SunRiseView(Context context) {
        this(context, null);

    }

    public SunRiseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        radius = DimenUtil.dp2px(context, radius);
        rectF = new RectF();
        initPaint();
    }

    private void initPaint() {

        mTimePaint = new Paint();
        mTimePaint.setColor(Color.WHITE);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(DimenUtil.sp2px(context, 15));

        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.WHITE);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStrokeWidth(DimenUtil.dp2px(context, 1));
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStrokeCap(Paint.Cap.ROUND);

        mBoldPaint = new Paint();
        mBoldPaint.setColor(Color.WHITE);
        mBoldPaint.setAntiAlias(true);
        mBoldPaint.setStrokeWidth(DimenUtil.dp2px(context, 4));
        mBoldPaint.setStyle(Paint.Style.STROKE);
        mBoldPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mBoldPaint.setStrokeCap(Paint.Cap.ROUND);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        rectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        canvas.drawArc(rectF, 180, 180, false, mCirclePaint);
        canvas.drawArc(rectF, 180, 180 * progress / (12 * 60), false, mBoldPaint);

        FontMetrics dm = mTextPaint.getFontMetrics();
        canvas.drawText("日出", (getWidth() - mTextPaint.measureText("日出")) / 2, getHeight() / 2, mTextPaint);
    }
}
