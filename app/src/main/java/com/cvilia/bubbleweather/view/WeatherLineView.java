package com.cvilia.bubbleweather.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.listener.ITempData;
import com.cvilia.bubbleweather.utils.DimenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * author: lzy
 * date: 2020/8/20
 * describe：描述
 */
public class WeatherLineView<T extends ITempData> extends View {

    private static final String TAG = WeatherLineView.class.getSimpleName();

    private Context mContext;

    //温度，点，线的画笔
    private Paint mTempPaint, mDotPaint, mLinePaint;


    private static final int TEMP2TOP = 8;
    private static final int TEMP2DOT = 5;
    private int temp2Top = -1;//表示文字距离view顶部/底部的高度
    private int temp2Dot = -1;//文字和温度点的距离

    private static final int DEFAULT_DOT_RADIUS = 2;
    private int dotRadisu = -1;

    //字体默认大小
    private static final int TEXT_SIZE = 12;
    private int textSize = -1;

    private List<T> datas = new ArrayList<>();

    //整个集合中的最高最低温度
    private int maxTemp, minTemp;

    //当前信息在集合中的位置
    private int position;

    private Paint.FontMetrics fontMetrics;

    private static final int DEFAULT_HEIGHT = 100;
    private static final int DEFAULT_WIDTH = 60;
    //整个view的宽高
    private int width = -1;
    private int height = -1;

    public void setDatas(List<T> datas, int maxTemp, int minTemp, int position) {

        for (int i = 0; i < datas.size(); i++) {
            Log.d("cvilias", "最高温度为" + datas.get(i).getMaxTemp() + "   ");
        }

        System.out.print("*********************************");
        for (int i = 0; i < datas.size(); i++) {
            Log.d("cvilias", "最低温度为" + datas.get(i).getMinTemp() + "   ");
        }

        this.datas = datas;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.position = position;
        postInvalidate();
    }

    public WeatherLineView(Context context) {
        super(context);
        init(context, null);
    }

    public WeatherLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * 初始化操作
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        if (attrs != null) {
            initAttrs(attrs);
        }
        initPaints();
        setAttrsPrority();
    }

    /**
     * 设置属性优先级
     */
    private void setAttrsPrority() {
        if (width == -1) {
            width = DimenUtil.dp2px(mContext, DEFAULT_WIDTH);
        }
        if (height == -1) {
            height = DimenUtil.dp2px(mContext, DEFAULT_HEIGHT);
        }
        if (textSize == -1) {
            textSize = DimenUtil.sp2px(mContext, TEXT_SIZE);
        }
        if (temp2Top == -1) {
            temp2Top = DimenUtil.dp2px(mContext, TEMP2TOP);
        }
        if (temp2Dot == -1) {
            temp2Dot = DimenUtil.dp2px(mContext, TEMP2DOT);
        }
        if (dotRadisu == -1) {
            dotRadisu = DimenUtil.dp2px(mContext, DEFAULT_DOT_RADIUS);
        }
    }

    /**
     * 初始化画笔
     */
    private void initPaints() {

        mDotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDotPaint.setStyle(Paint.Style.FILL);
        mDotPaint.setColor(Color.WHITE);

        mTempPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTempPaint.setColor(Color.WHITE);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setStrokeWidth(DimenUtil.dp2px(mContext, 1f));
        mLinePaint.setColor(Color.WHITE);

    }


    /**
     * 设置属性值
     *
     * @param attrs
     */
    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.WeatherLineView);
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.WeatherLineView_tempSize) {
                textSize = typedArray.getDimensionPixelSize(attr, TEXT_SIZE);
            }
        }
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        /**
         * 获取宽高的适配类型，UNSPECIFIED（不受约束），EXACTLY(match_parent),AT_MOST(wrap_content)
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int widthResult = getSize(widthSize, widthMode, 0);
        int heightResult = getSize(heightSize, heightMode, 1);
        setMeasuredDimension(widthResult, heightResult);

    }

    /**
     * @param size     宽/高大小
     * @param sizeMode 测量mode
     * @param type     0=width 1=height
     * @return
     */
    private int getSize(int size, int sizeMode, int type) {
        int result = -1;
        if (sizeMode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            if (type == 0) {
                result = width + getPaddingLeft() + getPaddingRight();
            } else {
                result = height + getPaddingBottom() + getPaddingTop();
            }
            if (sizeMode == MeasureSpec.AT_MOST) {
                result = Math.min(size, result);
            }
        }
        return result;
    }


    private int w, h;
    private int baseY;
    private int heightPerTemp;//温度每一度需要的高度

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (minTemp >= maxTemp) {
            Log.e(TAG, "maxTemp=" + maxTemp + "；minTemp=" + minTemp + "；最低温度小于或等于最高温度了，这是不允许的");
            return;
        }

        mTempPaint.setTextSize(textSize);
        fontMetrics = mTempPaint.getFontMetrics();

        //返回view的宽高
        w = getWidth();
        h = getHeight();

        //baseY表示文字距离顶部（上面的温度）/底部（下面的温度）+文字和dot的距离以及文字本身的高度
        baseY = temp2Top + temp2Dot + (int) (fontMetrics.bottom - fontMetrics.ascent);

        //baseHeight表示的是两个温度点的直径+两个点之间的距离,包含4各dotRadius
        int baseHeight = h - 2 * baseY;

        heightPerTemp = baseHeight / (maxTemp - minTemp);
        //*******************************************************************************************************

        T t = datas.get(position);
        Point[] dotPoints = getDotPoints(t);
        drawDots(canvas, dotPoints);

        Point[] tempBaseLinePoints = getTempBaseLinePoints(t, dotPoints);
        drawTempText(tempBaseLinePoints, canvas, t);


        //position>0的点都需要话左边的线
        if (position > 0) {
            T preT = datas.get(position - 1);
            Point[] leftPoints = getLeftPoints(dotPoints, preT);
            drawLine(dotPoints, leftPoints, canvas);
        }

        //只要不是最右边的点都需要话右边的线
        if (position < datas.size() - 1) {
            T nextT = datas.get(position + 1);
            Point[] nextPoints = getRightPoints(dotPoints, nextT);
            drawLine(dotPoints, nextPoints, canvas);
        }

    }


    private void drawLine(Point[] startPoints, Point[] endPoints, Canvas canvas) {
        canvas.drawLine(startPoints[0].x, startPoints[0].y, endPoints[0].x, endPoints[0].y, mLinePaint);
        canvas.drawLine(startPoints[1].x, startPoints[1].y, endPoints[1].x, endPoints[1].y, mLinePaint);
    }

    /**
     * 计算下一天的温度点坐标
     *
     * @param dotPoints
     * @param nextT
     * @return
     */
    private Point[] getRightPoints(Point[] dotPoints, T nextT) {
        Point[] points = new Point[2];
        Point[] prePoints = getDotPoints(nextT);
        //这里的计算，就是连接两个点，和两个view交接相交的点的坐标，然后两边都连接到这个点，因为是计算的是当前坐标的连线点，在当前view的左边界，所以x坐标就是0
        points[0] = new Point(w, (dotPoints[0].y + prePoints[0].y) / 2);
        points[1] = new Point(w, (dotPoints[1].y + prePoints[1].y) / 2);

        return points;
    }

    /**
     * 计算前一天的温度点的坐标
     *
     * @param dotPoints
     * @param preT
     * @return
     */
    private Point[] getLeftPoints(Point[] dotPoints, T preT) {

        Point[] points = new Point[2];
        Point[] prePoints = getDotPoints(preT);
        //这里的计算，就是连接两个点，和两个view交接相交的点的坐标，然后两边都连接到这个点，因为是计算的是当前坐标的连线点，在当前view的左边界，所以x坐标就是0
        points[0] = new Point(0, (dotPoints[0].y + prePoints[0].y) / 2);
        points[1] = new Point(0, (dotPoints[1].y + prePoints[1].y) / 2);

        return points;
    }

    /**
     * 画温度文本
     *
     * @param tempBaseLinePoints
     * @param canvas
     * @param t
     */
    private void drawTempText(Point[] tempBaseLinePoints, Canvas canvas, T t) {
        canvas.drawText(t.getMaxTemp() + "", tempBaseLinePoints[0].x, tempBaseLinePoints[0].y, mTempPaint);
        canvas.drawText(t.getMinTemp() + "", tempBaseLinePoints[1].x, tempBaseLinePoints[1].y, mTempPaint);
    }

    /**
     * 温度文字的起始坐标
     *
     * @param t
     * @param dotPoints
     * @return
     */
    private Point[] getTempBaseLinePoints(T t, Point[] dotPoints) {

        Point[] points = new Point[2];

        //通过计算上面点的纵坐标减去点的半径再减去文字距离点的距离就是文字的纵坐标
        int topY = dotPoints[0].y - dotRadisu - temp2Dot - Math.abs((int) (fontMetrics.bottom - fontMetrics.ascent));
        int topX = (w - (int) mTempPaint.measureText(t.getMaxTemp() + "")) / 2;

        Point topTemp = new Point(topX, topY);
        points[0] = topTemp;

        int bottomTempHeight = Math.abs((int) (fontMetrics.bottom - fontMetrics.ascent));
        int bottomY = dotPoints[1].y + dotRadisu + temp2Dot + bottomTempHeight / 3 * 2;
        int bottomX = (w - (int) mTempPaint.measureText(t.getMinTemp() + "")) / 2;
        Point bottomTemp = new Point(bottomX, bottomY);
        points[1] = bottomTemp;
        return points;
    }

    /**
     * 画温度点
     *
     * @param canvas
     * @param points
     */
    private void drawDots(Canvas canvas, Point[] points) {
        canvas.drawCircle(points[0].x, points[0].y, dotRadisu, mDotPaint);
        canvas.drawCircle(points[1].x, points[1].y, dotRadisu, mDotPaint);
    }

    /**
     * 计算当天（不是指今天）温度的坐标
     *
     * @param t
     * @return
     */
    private Point[] getDotPoints(T t) {

        Point[] points = new Point[2];
        //上面的点纵坐标
        int topY = baseY + dotRadisu + (maxTemp - t.getMaxTemp()) * heightPerTemp;
        Point topPoint = new Point(w / 2, topY);

        //下面点的纵坐标
        int bottomY = baseY + (t.getMaxTemp() - t.getMinTemp()) * heightPerTemp;
        Point bottomPoint = new Point(w / 2, bottomY);
        points[0] = topPoint;
        points[1] = bottomPoint;
        return points;
    }
}
