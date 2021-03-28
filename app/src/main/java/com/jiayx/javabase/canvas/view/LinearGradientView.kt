package com.jiayx.javabase.canvas.view

import android.accessibilityservice.GestureDescription
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewAnimationUtils
import androidx.annotation.RequiresApi
import kotlin.math.max
import kotlin.math.min

/**
 *Created by yuxi_
on 2021/3/25
 线性渐变
 */
class LinearGradientView (context: Context, attrs: AttributeSet?) : View(context,attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var strokeWidth = 0f
    private lateinit var shader: LinearGradient

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let { canvasGradient(canvas) }
    }


    private fun canvasGradient(canvas: Canvas?){
        // 计算 x，y 的起始点坐标
        val startX = paddingStart.toFloat()
        val endX = width.toFloat() - paddingEnd
        val startY = paddingTop.toFloat()
        val endY = height.toFloat() - paddingTop
        paint.strokeWidth = 20f
        strokeWidth = paint.strokeWidth / 2
        // 计算 X,Y 的最大最小点坐标
        val minX =  startX + strokeWidth
        val maxX =  endX - strokeWidth
        val minY = startY + strokeWidth
        val maxY = endY - strokeWidth
        /*paint.color = Color.BLUE
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas?.drawRect(minX,minY,maxX,maxY,paint)*/
        // 计算画布中心点的坐标
        val canvasWidth1 = endX - startX
        val canvasHeight2 = endY - startY
        val centerX =(canvasWidth1) / 2  + paddingStart
        val centerY = (canvasHeight2) /2 + paddingTop
        val radius1 = min(canvasWidth1,canvasHeight2) / 2 - strokeWidth
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        val midpoint = (endY + startY) / 2
        /*shader = LinearGradient(startX,midpoint,endX,midpoint,Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"),Shader.TileMode.MIRROR)
        paint.shader = shader
        canvas?.drawCircle(centerX,centerY - 500,radius1,paint)*/
        shader = LinearGradient(startX,midpoint,endX,midpoint,Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"),Shader.TileMode.MIRROR)
        paint.shader = shader
        canvas?.drawCircle(centerX,midpoint,radius1,paint)
       /* shader = LinearGradient(startX,midpoint,endX,midpoint,Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"),Shader.TileMode.REPEAT)
        paint.shader = shader
        canvas?.drawCircle(centerX,centerY,radius1-150,paint)*/

    }
}