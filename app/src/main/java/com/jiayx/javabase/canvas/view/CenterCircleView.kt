package com.jiayx.javabase.canvas.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import kotlin.math.min

/**
 *Created by yuxi_
on 2021/3/25
 */
class CenterCircleView (context: Context, attrs: AttributeSet?) : View(context,attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var strokeWidth = 0f

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        paint.strokeWidth = 5f

        strokeWidth = paint.strokeWidth / 2

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let { canvasCircle(it) }
    }

    private fun canvasCircle(canvas: Canvas?){
        //todo 计算 x,y 点的起始坐标点
        val startX = paddingStart.toFloat()
        val endX = width.toFloat() - paddingEnd
        val startY = paddingTop.toFloat()
        val endY = height.toFloat() - paddingBottom
        // 计算坐标的最大值，最小值
        val minX = startX + strokeWidth
        val maxX = endX - strokeWidth
        val minY = startY + strokeWidth
        val maxY = endY - strokeWidth
        paint.color = Color.parseColor("#80ff0000")
        paint.style = Paint.Style.FILL
        canvas?.drawRect(minX,minY,maxX,maxY,paint)
        paint.reset()
        paint.color = Color.GREEN
        paint.style = Paint.Style.FILL_AND_STROKE
        // 计算绘制区域
        val canvasWidth = endX - startX
        val canvasHeight = endY - startY
        // 计算绘制的中心店坐标
        val centerX = canvasWidth / 2 + paddingStart
        val centerY = canvasHeight / 2 + paddingTop
        // 计算圆的半径 绘制区域的最小值,减去线宽的一半，保证绘制完整
        val radius = min(canvasWidth,canvasHeight) / 2 - strokeWidth
        canvas?.drawCircle(centerX,centerY,radius,paint)

    }
}