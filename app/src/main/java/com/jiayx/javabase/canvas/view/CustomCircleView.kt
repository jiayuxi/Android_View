package com.jiayx.javabase.canvas.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import kotlin.math.min

/**
 *Created by yuxi_
on 2021/3/16
自定义圆
 */
class CustomCircleView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    // 初始化画笔
   private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
   private var halfStrokeWidth  = 0f

    // 初始化参数
    init {
        paint.style = Paint.Style.STROKE
        paint.color = Color.BLUE
        paint.strokeWidth = 5f
        halfStrokeWidth = paint.strokeWidth / 2
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        method_1(canvas)
    }
    private fun method_1(canvas: Canvas?) {
        // 计算x,y 起始坐标
        // x 的起点坐标 等于 paddingStart 间隔
        val startX = paddingStart.toFloat()
        // x 的终点坐标 等于 绘制区域的宽度 width - paddingEnd 间隔
        val endX = width.toFloat() - paddingEnd
        // y 的起点坐标等于 paddingTop 的间隔
        val startY = paddingTop
        // y 的终点坐标等于 绘制区域的高度 height - paddingBottom
        val endY = height.toFloat() - paddingBottom
        //X,Y 绘制区域的计算
        val canvasWidth = endX - startX
        val canvasHeight = endY - startY
        // 中心坐标的计算，整个画布的一半 加上 padding 间隔
        val conterx = canvasWidth / 2 + paddingStart
        val contery = canvasHeight / 2 + paddingTop
        //计算圆的半径，比较绘制区域的宽高，以最小的高的二分之一为半径，减去线宽，保证绘制的圆能正常的显示
        val radius = min(canvasWidth, canvasHeight)/2 - halfStrokeWidth
        
        canvas?.drawCircle(conterx, contery,radius, paint)
    }
}
