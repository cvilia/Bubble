package com.cvilia.bubbleweather.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
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
    private Paint mTimePaint, mTextPaint, mCirclePaint,mBoldPaint;
    private Context context;
    private int radius = 60;
    private int width = 2 * radius;
    private int height = radius;

    private float progress= 0;

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
        width = DimenUtil.dp2px(context, width);
        height = DimenUtil.dp2px(context, height);
        initPaint();
    }

    private void initPaint() {

        mTimePaint = new Paint();
        mTimePaint.setColor(Color.WHITE);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(DimenUtil.sp2px(context,15));

        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.WHITE);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStrokeWidth(3);
        mCirclePaint.setStyle(Paint.Style.STROKE);

        mBoldPaint = new Paint();
        mCirclePaint.setColor(Color.WHITE);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStrokeWidth(6);
        mCirclePaint.setStyle(Paint.Style.STROKE);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int widthResult = getSize(widthSize, widthMode, true);
        int heightResult = getSize(heightSize, heightMode, false);
        setMeasuredDimension(widthResult, heightResult);
    }

    private int getSize(int size, int mode, boolean isWidth) {
        int result = -1;
        if (mode == MeasureSpec.EXACTLY) {
            return size;
        } else {
            if (isWidth) {
                result = width + getPaddingLeft() + getPaddingRight();
            } else {
                result = height + getPaddingTop() + getPaddingBottom();
            }
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(size, result);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        FontMetrics dm = mTextPaint.getFontMetrics();
        canvas.drawArc(0,0,width,width,0,-180,false,mCirclePaint);
        canvas.drawText("日出",(width-mTextPaint.measureText("日出"))/2,height,mTextPaint);
        canvas.drawArc(new RectF(0,0,width,width),90,-180,false,mBoldPaint);
    }
}
