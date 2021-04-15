package com.jiayx.javabase.canvas.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup

/**
 *Created by yuxi_
on 2021/4/14
 */
class CustomViewGroup(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    private val OFFSET = 100
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //TODO 1、计算自身的尺寸
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //TODO 2、 为每个子view计算测量限制信息
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        //TODO 3、把上一个确定的限制信息传递给每一个子view，然后子view measure 自己的尺寸
        var childCount: Int = childCount
        //TODO for 循环，子 view measure 自己的尺寸,使用 getChildMeasureSpec(int spec, int padding, int childDimension)
        for (i in 0 until childCount) {
            var childAt: View? = getChildAt(i)
            var layoutParams = childAt?.layoutParams
            var childMeasureSpecWidth =
                layoutParams?.width?.let { getChildMeasureSpec(widthMeasureSpec, 0, it) }
            var childMeasureSpecHeight =
                layoutParams?.height?.let { getChildMeasureSpec(heightMeasureSpec, 0, it) }
            if (childMeasureSpecWidth != null && childMeasureSpecHeight != null) {
                childAt?.measure(childMeasureSpecWidth, childMeasureSpecHeight)
            }
        }
        //TODO 4、获取子view 测量好的尺寸
        //TODO 5、viewGroup根据自身情况计算尺寸
        var width = 0
        var height = 0
        when (widthMode) {
            MeasureSpec.EXACTLY -> {
                width = widthSize
            }
            MeasureSpec.AT_MOST, MeasureSpec.UNSPECIFIED -> {
                for (i in 0 until childCount) {
                    var childAt: View? = getChildAt(i)
                    childAt?.let {
                        var measuredWdith = i * OFFSET + it.measuredWidth
                        width = width.coerceAtLeast(measuredWdith)
                    }
                }
            }
            else -> {
            }
        }
        when (heightMode) {
            MeasureSpec.EXACTLY -> {
                height = heightSize
            }
            MeasureSpec.AT_MOST, MeasureSpec.UNSPECIFIED -> {
                for (i in 0 until childCount) {
                    var childAt: View? = getChildAt(i)
                    childAt?.let {
                        height += it.measuredHeight
                    }
                }
            }
            else -> {

            }
        }
        // TODO 6、保存自身尺寸
        setMeasuredDimension(width, height)
        Log.d("custom_view", "onMeasure: 子view的个数：$childCount")
    }

    // 确定位置
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        // TODO 1、遍历子view
        // TODO 2、确定自己的规则
        //TODO 3、子view测量尺寸
        // TODO 4、left,top,right,bottom 的计算
        // TODO 5、child.layout() 设置
        var left = 0
        var top = 0
        var right = 0
        var bottom = 0
        for (i in 0 until childCount) {
            var childAt: View? = getChildAt(i)
            childAt?.let {
                left = i * OFFSET
                right = left + it.measuredWidth
                bottom = top + it.measuredHeight
                childAt.layout(left, top, right, bottom)
                top += it.measuredHeight
            }
        }
    }
}