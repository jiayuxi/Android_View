package com.jiayx.javabase.canvas.view

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.*
import android.widget.EdgeEffect
import android.widget.OverScroller
import androidx.annotation.RequiresApi
import com.jiayx.javabase.canvas.ui.fragment.util.ToolUtils


/**
 *Created by yuxi_
on 2021/4/15
自定义 流式布局
 */
class FlowLayoutView : ViewGroup {

    private var lineViews = ArrayList<View>()

    // 定义一个list 存放行数，一行一行的存储
    private val views by lazy { ArrayList<List<View>>() }

    // 定义一个list 存放行高
    private val heights by lazy { ArrayList<Int>() }

    // 定义一个变量，判断是否可以滑动
    private var scrollable = false

    // 定义一个变量，代表本身的测量高度
    private var measureHeight = 0

    // 定义一个变量，代表内容的高度
    private var realHeight = 0

    // 创建一个变量
    private var mTouchSlop = 0

    // 定义一个对象 OverScroller
    private lateinit var overScroller: OverScroller

    // 边缘最顶部
    private lateinit var mEdgeEGlopTop: EdgeEffect

    // 边缘最底部
    private lateinit var mEdgeGlopBottom: EdgeEffect

    // 速度追踪器
    private lateinit var velocityTracker: VelocityTracker

    // 滑动的最低速度
    private var mMinimumVelocity = 0

    // 滑动的最大速度
    private var mMaximumVelocity = 0

    // 变量滚动的距离
    private var mOverScrollDistance = 0

    // 变量抛动的距离
    private var mOverFlingDistance = 0

    // 定义变量 是否被拖动
    private var mIsBeingDragged = false

    //
    private val INVALID_POINTER = -1

    // 定义 指针ID
    private var mActivePointerId = INVALID_POINTER

    // 定义一个 移动最后的 y 坐标
    private var mLastMotionY = 0

    //
    private val mScrollOffset = IntArray(2)

    //
    private val mScrollConsumed = IntArray(2)

    //
    private var mNestedYOffset = 0

    constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs) {
        initFlowLayout()
    }

    private fun initFlowLayout() {
        overScroller = OverScroller(context)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            focusable.also { true }
        }
        descendantFocusability = FOCUS_AFTER_DESCENDANTS
        willNotDraw().also { false }
        // 包含到用户界面中用于超时、大小和距离的标准常量的方法。
        var viewConfiguration = ViewConfiguration.get(context)
        //在我们认为用户正在滚动之前，触摸的像素距离可能会徘徊
        mTouchSlop = viewConfiguration.scaledTouchSlop
        //发起抛投的最小速度，以每秒像素为单位。
        mMinimumVelocity = viewConfiguration.scaledMinimumFlingVelocity
        //发起抛投的最大速度，以每秒像素衡量。
        mMaximumVelocity = viewConfiguration.scaledMaximumFlingVelocity
        //显示边缘效果时视图应该覆盖的最大距离
        mOverScrollDistance = viewConfiguration.scaledOverscrollDistance
        //当显示边缘效果时，视图应该超越的最大距离
        mOverFlingDistance = viewConfiguration.scaledOverflingDistance
    }

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
                    // TODO 如果一行只有一个元素，给定限定值
                    if (lineViews.size == 1 && lineViews[0].layoutParams.height == LayoutParams.MATCH_PARENT) {
                        lineHeight = ToolUtils.dp2px(150)
                    }
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
                // 判断子 view 的高度，如果是 MATCH_PARENT ,暂时不做处理
                if (lp.height != LayoutParams.MATCH_PARENT) {
                    //获取最大的行高
                    lineHeight = lineHeight.coerceAtLeast(childHeight)
                }
                if (i == childCount - 1) {
                    flowHeight += lineHeight
                    flowWidth = flowWidth.coerceAtLeast(lineWidth)
                    heights.add(lineHeight)
                    views.add(lineViews)
                }
            }
        }
        recalculateChild(widthMeasureSpec, heightMeasureSpec)
        //设置 flowLayout 最终的宽高
        val widthMeasure = if (widthMode == MeasureSpec.EXACTLY) widthSize else flowWidth
        val heightMeasure = if (heightMode == MeasureSpec.EXACTLY) heightSize else flowHeight
        setMeasuredDimension(widthMeasure, heightMeasure)
    }

    /**
     * 重新计算行高
     */
    private fun recalculateChild(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //获取行数大小
        var lineSize = views.size
        for (i in 0 until lineSize) {
            //获取行高
            var lineHeight = heights[i]
            // 获取每一行的view
            var lineView = views[i]
            //获取每一行 view 的个数
            var size = lineView.size
            // 循环遍历子 view 个数
            for (i in 0 until size) {
                //获取子 view
                var childView = lineView[i]
                childView.let {
                    // 获layoutParams
                    var layoutParams = it.layoutParams
                    if (layoutParams.height == LayoutParams.MATCH_PARENT) {
                        var childMeasureSpecWidth =
                            getChildMeasureSpec(widthMeasureSpec, 0, layoutParams.width)
                        var childMeasureSpecHeight =
                            getChildMeasureSpec(heightMeasureSpec, 0, lineHeight)
                        it.measure(childMeasureSpecWidth, childMeasureSpecHeight)
                    }
                }
            }
        }
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