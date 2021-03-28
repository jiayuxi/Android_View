package com.jiayx.javabase.canvas.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.annotation.HalfFloat
import java.lang.Exception
import kotlin.math.max
import kotlin.math.min

/**
 *Created by yuxi_
on 2021/3/10
 自定义 view
 绘制网格 使用中心点
 */
class GridView(context : Context, attrs: AttributeSet? = null) : View(context,attrs) {
    //初始化画笔
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    // 列数
    private var column = 36
    private var halfWidth = 0f

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        // 设置绘画笔参数
        paint.color = Color.GRAY
        paint.strokeWidth = 1f
        halfWidth = paint.strokeWidth/2
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let { canvasGrid(it) }
    }
    
    /**
     * 绘制网格
     */
    private fun  canvasGrid(canvas: Canvas?){
        //计算坐标参数
        try {
            // x 的起点坐标 为 paddingStart 间隔
            val startX = paddingStart.toFloat()
            // x 的终点坐标为绘制区域width - paddingEnd间隔
            val endX = width.toFloat() - paddingEnd
            // Y 的起点坐标为 paddingTop 间隔
            val startY = paddingTop.toFloat()
            // Y 的终点坐标为 绘制区域的高度 height - paddingBottom 间距
            val endY = height.toFloat() - paddingBottom
            // 计算坐标的最大值，最小值
            val minX = startX + halfWidth
            val maxX = endX - halfWidth
            val minY = startY + halfWidth
            val maxY = endY - halfWidth
            // 获取绘制区域
            val canvasWidth = endX - startX
            val canvasHeight = endY - startY
            // 获取画布的中心点
            val centerX = canvasWidth / 2 + paddingStart
            val centerY = canvasHeight / 2 + paddingTop
            // 获取横竖网格之间的间隔数 ，最大点 坐标 减去 最小点坐标 之间的距离 ，除以条数
            val spaceX = (maxX - minX) / column
            val spaceY = (maxY - minY) / column
            // 求取最小的间隔
            val relSpace = min(spaceX, spaceY)
            // 已中心点 画网格，所有，需要使用 可绘制区域 的一半 求取最大 横竖条数
            //列数
            val columnLines = ((maxX - minX) / 2 / relSpace).toInt()
            // 行数
            val rowLines = ((maxY - minY) / 2 / relSpace).toInt()
            // 求取最大条数
            val maxLines = max(columnLines, rowLines)
            //
            paint.color = Color.parseColor("#6600ff00")
            for (i in 0..maxLines) {
                val offset = relSpace * i
                // 纵线
                if (i <= columnLines) {

                    canvas?.drawLine(centerX - offset, startY, centerX - offset, endY, paint)
                    if (i > 0) {
                        canvas?.drawLine(centerX + offset, startY, centerX + offset, endY, paint)
                    }
                }
                // 横线
                if (i <= rowLines) {
                    canvas?.drawLine(startX, centerY - offset, endX, centerY - offset, paint)
                    if (i > 0) {
                        canvas?.drawLine(startX, centerY + offset, endX, centerY + offset, paint)
                    }
                }
            }
        }catch (ex:Exception){
            println("error ${ex.message}")
        }
    }
}