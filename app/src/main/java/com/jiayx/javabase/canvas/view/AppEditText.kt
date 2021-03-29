package com.jiayx.javabase.canvas.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText


/**
 *Created by yuxi_
on 2021/3/29
 */
class AppEditText(context: Context,attrs: AttributeSet?) :  AppCompatEditText(context,attrs) {

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawColor(Color.parseColor("#66BB6a"))
        super.onDraw(canvas)
    }
}