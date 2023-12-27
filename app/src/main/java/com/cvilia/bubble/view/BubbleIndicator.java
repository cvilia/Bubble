package com.cvilia.bubble.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.cvilia.bubble.R;

public class BubbleIndicator extends View {

    // 指示器宽高
    private float mIndicatorWidth;
    private float mIndicatorHeight;

    // 指示器 item宽高
    private float mIndicatorItemWidth;
    private float mIndicatorItemHeight;

    private float mIndicatorItemMargin;
    private int mIndicatorCount;

    // 选中和未选中状态的画笔
    private Paint mSelectedPaint;
    private Paint mUnselectedPaint;

    // 当前选中的位置
    private int mCurrentPosition = 0;

    private boolean isCircle = true;

    private float mStartDrawPosition = 0f;

    private Context mContext;

    private int mSelectedColor = Color.WHITE;
    private int mUnselectedColor = Color.GRAY;

    public BubbleIndicator(Context context) {
        super(context);
        init(context, null);
    }

    public BubbleIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        if (attrs != null) {
            initAttrs(attrs);
        }
        initPaint();
    }

    private void initPaint() {

        mSelectedPaint = new Paint();
        mSelectedPaint.setStyle(Paint.Style.FILL);
        mSelectedPaint.setAntiAlias(true);
        mSelectedPaint.setColor(mSelectedColor);

        mUnselectedPaint = new Paint();
        mUnselectedPaint.setStyle(Paint.Style.STROKE);
        mUnselectedPaint.setColor(mUnselectedColor);
        mUnselectedPaint.setAntiAlias(true);

    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.BubbleIndicator);
        mSelectedColor = ta.getColor(R.styleable.BubbleIndicator_selectedColor, Color.WHITE);
        mUnselectedColor = ta.getColor(R.styleable.BubbleIndicator_unselectedColor, Color.GRAY);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mIndicatorWidth = MeasureSpec.getSize(widthMeasureSpec);
        mIndicatorHeight = MeasureSpec.getSize(heightMeasureSpec);
        mIndicatorItemWidth = mIndicatorWidth / (mIndicatorCount + mIndicatorCount - 1);
        mIndicatorItemHeight = Math.min(mIndicatorItemWidth, mIndicatorHeight);

        mStartDrawPosition = mIndicatorWidth / 2f - ((mIndicatorCount + mIndicatorCount - 1) * mIndicatorItemHeight) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制起始位置的纵坐标
        float dy = mIndicatorHeight / 2f;
        // 绘制item的半径
        float cr = mIndicatorItemHeight / 2f;
        //  绘制所有的item
        for (int i = 0; i < mIndicatorCount; i++) {
            mIndicatorItemMargin = mIndicatorItemHeight;
            // 动态计算每个item的横坐标
            float dx = mStartDrawPosition + cr + mIndicatorItemMargin * i + mIndicatorItemMargin * i;
            canvas.drawCircle(dx, dy, cr, i == mCurrentPosition ? mSelectedPaint : mUnselectedPaint);
        }
    }

    public void setIndicatorCount(int count) {
        mIndicatorCount = count;
        if (mCurrentPosition >= mIndicatorCount) {
            mCurrentPosition = mIndicatorCount - 1;
        }
        if (mIndicatorCount <= 1) {
            setVisibility(View.GONE);
        } else {
            setVisibility(View.VISIBLE);
        }
    }

    public void setCurrentPosition(int index){
        this.mCurrentPosition = index;
    }
}
