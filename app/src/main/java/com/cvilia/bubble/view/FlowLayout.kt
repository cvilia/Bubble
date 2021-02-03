package com.cvilia.bubble.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.cvilia.bubble.utils.DisplayUtil

/**
 * author: lzy
 * date: 2020/9/27
 * describe：流式布局，需要手动挨个儿添加子view
 *
 */

class FlowLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ViewGroup(context, attrs, defStyleAttr) {

    //记录一共有多少行
    private val mLines = mutableListOf<List<View>>()

    //记录每一行的最大高度，layout阶段使用
    private val mLineHeight = mutableListOf<Int>()

    private val mHorizontalSpace = DisplayUtil.dp2px(context, 16f)
    private val mVerticalSpace = DisplayUtil.dp2px(context, 8f)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        //注意要点：由于测量过程是从父控件到子控件递归调用的，所以onMeasure可能被调用多次，这里参考FrameLayout源码进行清除工作
        mLines.clear()
        mLineHeight.clear()

        /**
         * 思路:1.先确定子控件的childWidthMeasureSpec与childHeightMeasureSpec(重点)
         *     2.在根据childWidthMeasureSpec与childHeightMeasureSpec测量子控件
         *     3.根据流式布局的算法计算出最大行宽和行高
         *     4.获取自身的测量模式，再根据子View的测量结果来确定自身的最终宽高
         */

        //获取父控件给的宽度
        val selfWidth = MeasureSpec.getSize(widthMeasureSpec)
        //获取父控件给的高度
        val selfHeight = MeasureSpec.getSize(heightMeasureSpec)

        //每一行已使用的宽度
        var lineWidthUsed = 0
        //每一行的最大高度
        var lineHeight = 0

        //自身所需的宽度
        var parentNeedWidth = 0
        //自身所需的高度
        var parentNeedHeight = 0

        //记录每一行控件的个数
        var lineViews = mutableListOf<View>()

        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            //对应的布局参数
            var layoutParams = childView.layoutParams

            //父控件的MeasureSpec、父控件已使用的padding，layoutparams共同确定子控件的MeasureSpec
            val childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, layoutParams.width)
            val childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingBottom + paddingTop, layoutParams.height)

            //测量子控件
            childView.measure(childWidthMeasureSpec, childHeightMeasureSpec)

            //测量完成后可获取测量的宽高
            val measureWidth = childView.measuredWidth
            val measureHeight = childView.measuredHeight

            //判断是否需要换行
            if (measureWidth + lineWidthUsed + mHorizontalSpace > selfWidth) {
                //记录行数
                mLines.add(lineViews)

                //记录行高
                mLineHeight.add(lineHeight)

                //每次换行时计算自身所需要的宽高
                parentNeedWidth = parentNeedWidth.coerceAtLeast(lineWidthUsed + mHorizontalSpace)
                parentNeedHeight += lineHeight + mVerticalSpace

                lineViews = mutableListOf()
                lineWidthUsed = 0
                lineHeight = 0
            }

            //记录每一行存放的控件
            lineViews.add(childView)

            //记录每一行使用的宽度
            lineWidthUsed += measureWidth + mHorizontalSpace

            //记录每一行的最大高度
            lineHeight = lineHeight.coerceAtLeast(measureHeight)

        }
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        setMeasuredDimension(
                if (widthMode == MeasureSpec.EXACTLY) selfWidth else parentNeedWidth,
                if (heightMode == MeasureSpec.EXACTLY) selfHeight else parentNeedHeight
        )


    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        var curL = paddingLeft
        var curT = paddingTop
        for (i in 0 until mLines.size) {
            val lineViews = mLines[i]
            val lineHeight = mLineHeight[i]


            lineViews.forEach { view ->
                val left = curL
                val top = curT
                val right = left + view.measuredWidth
                val bottom = top + view.measuredHeight

                //注意要点：在onMeasure之后能够获取measuredWidth或measuredHeight，但获取width/height无效，必须在view.layout之后才生效
                view.layout(left, top, right, bottom)

                //计算下一个空间的left
                curL = right + mHorizontalSpace
            }

            curL = paddingLeft
            curT += lineHeight + mVerticalSpace

        }

    }


}