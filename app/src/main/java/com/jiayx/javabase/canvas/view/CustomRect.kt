package com.jiayx.javabase.canvas.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

/**
 *Created by yuxi_
on 2021/3/22
 */
class CustomRect(context: Context,attrs:AttributeSet?) : View(context,attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mStrokeWidth = 0f
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        paint.strokeWidth = 5f
        mStrokeWidth = paint.strokeWidth/2
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val startX = paddingStart.toFloat()
        val endX = width.toFloat() - paddingEnd
        val startY = paddingTop.toFloat()
        val endY = height.toFloat() - paddingBottom
        // 计算坐标的最大值，最小值
        val minX = startX + mStrokeWidth
        val maxX = endX - mStrokeWidth
        val minY = startY + mStrokeWidth
        val maxY = endY - mStrokeWidth
        //绘制区域
        val widthCanvas = maxX - minX
        val heidthCnavas = maxY - minY
        var min = min(widthCanvas, heidthCnavas)
        paint.color =Color.GREEN
        canvas?.drawRect(minX,minY,maxX,maxY,paint)
        // 圆 绘制区域的计算
        val canvasWidth = endX - startX
        val canvasHeight = endY - startY;
        // 圆中心点的坐标
        val centerX = canvasWidth/2 + paddingStart
        val centerY = canvasHeight/2 + paddingTop
        //圆半径的获取
        val radius = min(canvasWidth,canvasHeight)/2 - mStrokeWidth
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.color = Color.BLUE
        canvas?.drawCircle(centerX,centerY,radius,paint)
    }
}