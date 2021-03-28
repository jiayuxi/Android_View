package com.jiayx.javabase.canvas.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.SweepGradient
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

/**
 *Created by yuxi_
on 2021/3/28
 */
class SweepGradientView (context: Context, attrs: AttributeSet?) : View(context,attrs) {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
         val paint = Paint(Paint.ANTI_ALIAS_FLAG)
          paint.style = Paint.Style.FILL

         // x 的起点坐标 为 paddingStart 间隔
         val startX = paddingStart.toFloat()
         // x 的终点坐标为绘制区域width - paddingEnd间隔
         val endX = width.toFloat() - paddingEnd
         // Y 的起点坐标为 paddingTop 间隔
         val startY = paddingTop.toFloat()
         // Y 的终点坐标为 绘制区域的高度 height - paddingBottom 间距
         val endY = height.toFloat() - paddingBottom
         // 计算坐标的最大值，最小值
         val minX = startX
         val maxX = endX
         val minY = startY
         val maxY = endY
         // 获取绘制区域
         val canvasWidth = endX - startX
         val canvasHeight = endY - startY
         // 获取画布的中心点
         val centerX = canvasWidth / 2 + paddingStart
         val centerY = canvasHeight / 2 + paddingTop
         val radius = min(canvasWidth,canvasHeight)/2
        val sweepGradient =  SweepGradient(centerX,centerY,
            Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"))
        paint.shader = sweepGradient
        canvas?.drawCircle(centerX,centerY,radius,paint)
    }

}