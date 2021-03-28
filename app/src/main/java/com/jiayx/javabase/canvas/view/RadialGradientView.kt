package com.jiayx.javabase.canvas.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

/**
 *Created by yuxi_
on 2021/3/28
 辐射渐变
 */
class RadialGradientView (context: Context, attrs: AttributeSet?) : View(context,attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var strokeWidth = 0F
    private lateinit var radialGradient: RadialGradient
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        paint.style = Paint.Style.FILL
       /* paint.strokeWidth = 5f
        strokeWidth = paint.strokeWidth/ 2*/
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
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
        val midpoint = (endY + startY)/2
        val radius1 = min(canvasWidth,canvasHeight) / 2 - strokeWidth
       radialGradient = RadialGradient(centerX,centerY - 600f,radius1/2,Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"),Shader.TileMode.CLAMP)
        paint.shader = radialGradient
        canvas?.drawCircle(centerX,centerY - 600f,radius1/2,paint)
        paint.reset()
       radialGradient = RadialGradient(centerX,centerY ,radius1/3,Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"),Shader.TileMode.REPEAT)
        paint.shader = radialGradient
        canvas?.drawCircle(centerX,centerY ,radius1/2,paint)
        paint.reset()
        radialGradient = RadialGradient(centerX,centerY + 600,radius1/3,Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"),Shader.TileMode.MIRROR)
        paint.shader = radialGradient
        canvas?.drawCircle(centerX,centerY + 600,radius1/2,paint)

    }
}