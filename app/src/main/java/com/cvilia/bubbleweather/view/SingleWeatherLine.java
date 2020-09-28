package com.cvilia.bubbleweather.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.listener.ITempData;
import com.cvilia.bubbleweather.utils.DimenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * author: lzy
 * date: 2020/9/22
 * describe：温度单曲线
 * 温度点的计算，是给出七小时内最高温和最低温的平均值，然后根据平均值进行计算
 */
public class SingleWeatherLine<T extends ITempData> extends View {

    private static final String TAG = SingleWeatherLine.class.getSimpleName();

    private Context mContext;

    //温度，点，线的画笔
    private Paint mTempPaint, mDotPaint, mLinePaint;

    private static final int DEFAULT_HEIGHT = 80;
    private static final int DEFAULT_WIDTH = 60;

    //整个view的宽高
    private int width = -1;
    private int height = -1;

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

    public void setDatas(List<T> datas, int position) {
        this.datas = datas;
        this.position = position;
        getExtremeTemp(datas);
        postInvalidate();
    }


    /**
     * 计算最大和最小天气
     *
     * @param infos
     */
    private void getExtremeTemp(List<T> infos) {

        int[] nums = new int[infos.size()];
        for (int i = 0; i < infos.size(); i++) {
            nums[i] = infos.get(i).getNormalTemp();
        }

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int s = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = s;
                }
            }
        }
        this.maxTemp = nums[nums.length - 1];
        this.minTemp = nums[0];
    }


    public SingleWeatherLine(Context context) {
        super(context);
        init(context, null);

    }

    public SingleWeatherLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        if (attrs != null) {
            initAttrs(attrs);
        }
        initPaints();
        setAttrsPrority();
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

    /**
     * 设置属性优先级
     */
    private void setAttrsPrority() {
        if (width == -1) {
            width = DimenUtil.dp2px(mContext, DEFAULT_WIDTH);
        } else {
            width = DimenUtil.dp2px(mContext, width);
        }
        if (height == -1) {
            height = DimenUtil.dp2px(mContext, DEFAULT_HEIGHT);
        } else {
            height = DimenUtil.dp2px(mContext, height);
        }
        if (textSize == -1) {
            textSize = DimenUtil.sp2px(mContext, TEXT_SIZE);
        } else {
//            textSize = DimenUtil.sp2px(mContext, textSize);
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
        mLinePaint.setAlpha(100);

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

    /**
     * @param size    宽/高
     * @param mode    测量mode
     * @param isWidth 是否是宽
     * @return 最终宽/高
     */

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

    private int w, h;
    private int baseY;
    private int heightPerTemp;//每一摄氏度需要的高度
    private int centerY;//最大温度和最低温度平均值的纵坐标
    private int centerTemp;//最大温度和最低温度的平均值

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTempPaint.setTextSize(textSize);
        fontMetrics = mTempPaint.getFontMetrics();

        w = getWidth();
        h = getHeight();

        //baseY表示文字距离顶部（上面的温度）/底部（下面的温度）+文字和dot的距离以及文字本身的高度
        baseY = temp2Top + temp2Dot + (int) (fontMetrics.bottom - fontMetrics.ascent);

        //除文字外的剩余高度
        int remainHeight = h - baseY;
        heightPerTemp = remainHeight / (2 * (maxTemp - minTemp));

        centerY = baseY + (h - baseY) / 2;
        centerTemp = (maxTemp + minTemp) / 2;


        T t = datas.get(position);

        Point dotPoint = getDotPoints(t);
        canvas.drawCircle(dotPoint.x, dotPoint.y, dotRadisu, mDotPaint);

        Point tempPoint = getTempPoint(t, dotPoint);
        canvas.drawText(t.getNormalTemp() + "℃", tempPoint.x, tempPoint.y, mTempPaint);

        if (position > 0) {//除了第一个点，所有点画左边的曲线
            T preT = datas.get(position - 1);
            Point prePoint = getDotPoints(preT);
            canvas.drawLine(0, (float) (prePoint.y + dotPoint.y) / 2, dotPoint.x, dotPoint.y, mLinePaint);
        }

        if (position < datas.size() - 1) {//除左后一个点外，画右边的线
            T nextT = datas.get(position + 1);
            Point nextPoint = getDotPoints(nextT);
            canvas.drawLine(dotPoint.x, dotPoint.y, w, (float) (dotPoint.y + nextPoint.y) / 2, mLinePaint);
        }

    }

    /**
     * 画左边的线
     *
     * @param dotPoint
     * @param prePoint
     * @param canvas
     */
    private void drawLeftLine(Point dotPoint, Point prePoint, Canvas canvas) {

    }

    /**
     * 计算温度文本的坐标
     *
     * @param t
     * @param dotPoint
     * @return
     */
    private Point getTempPoint(T t, Point dotPoint) {

        int y = dotPoint.y - dotRadisu - temp2Dot;
        int x = (w - (int) mTempPaint.measureText(t.getNormalTemp() + "")) / 2;

        return new Point(x, y);
    }

    /**
     * 计算每一个温度点的坐标
     *
     * @param t
     * @return
     */
    private Point getDotPoints(T t) {

        int y = baseY + (h - baseY) / 2;//默认纵坐标
        if (t.getNormalTemp() > centerTemp) {
            y = centerY - Math.abs((t.getNormalTemp() - centerTemp) * heightPerTemp);
        } else if (t.getNormalTemp() < centerTemp) {
            y = centerY + Math.abs((t.getNormalTemp() - centerTemp) * heightPerTemp);
        }

        return new Point(w / 2, y);
    }
}
