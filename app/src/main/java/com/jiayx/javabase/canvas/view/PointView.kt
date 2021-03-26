package com.jiayx.javabase.canvas.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 *Created by yuxi_
on 2021/3/24
 绘制原点
 */
class PointView(context: Context, attrs: AttributeSet) : View(context,attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        paint.color = Color.RED
        paint.style = Paint.Style.FILL

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let { canvasPoint(it) }
    }
    private fun canvasPoint(canvas: Canvas?){
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
        //绘制圆点
        paint.reset();
        paint.color = Color.GREEN
        paint.strokeWidth = 50f
        paint.strokeCap = Paint.Cap.ROUND
        canvas?.drawPoint(startX + 200f,startY + 200f,paint)
        paint.color = Color.BLACK
        canvas?.drawPoint(startX + 500f,startY + 200f,paint)
        paint.color = Color.BLUE
        canvas?.drawPoint(startX+200f,startY + 400f,paint)
        paint.color = Color.DKGRAY
        canvas?.drawPoint(startX + 500f,startY + 400f,paint)
        // 绘制方形
        paint.color = Color.BLACK
        paint.strokeCap = Paint.Cap.SQUARE
        canvas?.drawPoint(startX + 200f,startY + 600f,paint)
        paint.color = Color.YELLOW
        canvas?.drawPoint(startX + 500f,startY + 600f,paint)
        paint.color = Color.GREEN
        canvas?.drawPoint(startX+200f,startY + 800f,paint)
        paint.color = Color.DKGRAY
        canvas?.drawPoint(startX + 500f,startY + 800f,paint)
        //绘制平头
        paint.color = Color.parseColor("#ff0000")
        paint.strokeCap = Paint.Cap.BUTT
        canvas?.drawPoint(startX + 200,startY + 1000f,paint)
        canvas?.drawPoint(startX + 500F,startY + 1000f,paint)
        canvas?.drawPoint(startX + 200f,startY + 1200f,paint)
        canvas?.drawPoint(startX + 500f,startY + 1200,paint)
        // 批量画点
        paint.color = Color.BLACK
        paint.strokeCap = Paint.Cap.ROUND
        var floatArrayOf = floatArrayOf(0f, 0f, 200f, 1400f, 200f, 1600f, 500f, 1400f, 500f, 1600f, 800f, 1400f, 800f, 1600f)
        //同样是画点，它和 drawPoint() 的区别是可以画多个点。pts 这个数组是点的坐标，
        // 每两个成一对；offset 表示跳过数组的前几个数再开始记坐标；
        // count 表示一共要绘制几个点。
        //drawPoints(float[] pts, int offset, int count, Paint paint)
        // / drawPoints(float[] pts, Paint paint) 画点（批量）
        canvas?.drawPoints(floatArrayOf,2,12,paint)
    }
}