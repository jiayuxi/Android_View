package com.jiayx.javabase.canvas.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.jiayx.javabase.R

/**
 *Created by yuxi_
on 2021/3/29
 */
class ClipRectView (context: Context, attrs: AttributeSet?) : View(context,attrs) {
     private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
     private val path = Path()

     private val bitmap by lazy {
        BitmapFactory.decodeResource(context.resources, R.mipmap.a148)
     }

    /**
     * 初始化对象
     */
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d("clip_rect", "onAttachedToWindow: ")
    }

    /**
     * 回收对象
     */
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d("clip_rect", "onDetachedFromWindow: ")
    }

    /**
     *
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d("clip_rect", "onMeasure: ")
    }

    /**
     * 主绘制
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d("clip_rect", "onDraw: ")
        canvas?.save()
        canvas?.clipRect(0f,0f,bitmap.width.toFloat() ,bitmap.height.toFloat())
        canvas?.drawBitmap(bitmap,100f,100f,paint)
        canvas?.restore()
    }

    /**
     * 绘制子View 方法
     */
    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        Log.d("clip_rect", "dispatchDraw: ")
    }

    override fun onDrawForeground(canvas: Canvas?) {
        super.onDrawForeground(canvas)
        Log.d("clip_rect", "onDrawForeground: ")
    }


}