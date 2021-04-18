package com.jiayx.javabase.canvas.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.textclassifier.TextClassifierEvent

/**
 *Created by yuxi_
on 2021/4/15
自定义 流式布局
 */
class FlowLayoutView(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private var lineViews = ArrayList<View>()

    //定义一个list 存放行数，一行一行的存储
    private val views by lazy { ArrayList<List<View>>() }

    // 定义一个list 存放行高
    private val heights by lazy { ArrayList<Int>() }

    //测量
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // 计算限制
        var widthMode = MeasureSpec.getMode(widthMeasureSpec)
        var widthSize = MeasureSpec.getSize(widthMeasureSpec)
        var heightMode = MeasureSpec.getMode(heightMeasureSpec)
        var heightSize = MeasureSpec.getSize(heightMeasureSpec)
        //定义变量，记录当前的宽和高
        var lineWidth = 0 // 当前子 view 的宽度之和
        var lineHeight = 0 // 当点子 view 中高度最高的
        //定义变量，流式布局的宽度和高度
        var flowWidth = 0 // 所有行中，宽度最大值
        var flowHeight = 0 // 所有行高度的累加
        //获取子 view 的个数
        val childCount = childCount
        //遍历子view
        for (i in 0 until childCount) {
            //获取子 view
            var childView: View? = getChildAt(i)
            childView?.let {
                //测量子 view ，获取当前子 view 的测量高度和宽度
                measureChild(it, widthMeasureSpec, heightMeasureSpec)
                //获取当前子 view 的宽度和高度
                var childWidth = it.measuredWidth
                var childHeight = it.measuredHeight
                val lp = it.getLayoutParams()
                //计算当前行的剩余宽度是否可以容纳下一个 view ，如果放不下，换行，保存当前的view，累加行高
                if (lineWidth + childWidth > widthSize) {//换行
                    //保存当前的行
                    views.add(lineViews)
                    //创建新的一行的集合
                    lineViews = ArrayList<View>()
                    //获取当前行最宽的值
                    flowWidth = flowWidth.coerceAtLeast(lineWidth)
                    //累加行的高度
                    flowHeight += lineHeight
                    //保存行的高度
                    heights.add(lineHeight)
                    //新的一行，当前行的宽度和高度置为零
                    lineWidth = 0
                    lineHeight = 0
                }
                // 保存当前的子 view对象
                lineViews.add(childView)
                //累加行的宽度
                lineWidth += childWidth
                //获取最大的行高
                lineHeight = lineHeight.coerceAtLeast(childHeight)
                if (i == childCount - 1) {
                    flowHeight += lineHeight
                    flowWidth = flowWidth.coerceAtLeast(lineWidth)
                    heights.add(lineHeight)
                    views.add(lineViews)
                }
            }
        }
        //设置 flowLayout 最终的宽高
        val widthMeasure = if (widthMode == MeasureSpec.EXACTLY) widthSize else flowWidth
        val heightMeasure = if (heightMode == MeasureSpec.EXACTLY) heightSize else flowHeight
        setMeasuredDimension(widthMeasure, heightMeasure)
    }

    //布局
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        // 获取所有的行数
        val lineCount = views.size
        //定义当前的变量
        var currX = 0
        var currY = 0
        //循环遍历所有的行数，一行一行的获取
        for (i in 0 until lineCount) {
            //获取每一行
            var lineView = views[i]
            //获取这行的高度值
            var lineHeight = heights[i]
            //遍历当前行的子 view
            var size = lineView.size
            for (i in 0 until size) {
                //获取每一行的 view
                var childView = lineView[i]
                val left = currX
                val top = currY
                val right = left + childView.measuredWidth
                val bottom = top + childView.measuredHeight
                childView.layout(left, top, right, bottom)
                //确定下一个 view 的 left
                currX += childView.measuredWidth
            }
            currY += lineHeight
            currX = 0
        }
    }
}