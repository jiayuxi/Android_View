package com.jiayx.javabase.canvas.ui.fragment.util

import android.content.res.Resources
import android.util.TypedValue

/**
 *Created by yuxi_
on 2021/4/20
 */
object ToolUtils {
    fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            Resources.getSystem().getDisplayMetrics()
        )
            .toInt()
    }
}