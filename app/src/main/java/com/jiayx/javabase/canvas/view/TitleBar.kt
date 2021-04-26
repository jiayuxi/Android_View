package com.jiayx.javabase.canvas.view

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.jiayx.javabase.R

/**
 *Created by yuxi_
on 2021/4/25
 */
class TitleBar : RelativeLayout {

    private var titleColor: Int? = Color.BLUE
    private var titleBg: Int? = Color.WHITE
    private var titleContent: String? = ""
    private var iv_titleBar_left: ImageView? = null
    private var iv_titleBar_right: ImageView? = null
    private var tv_titleBar_title: TextView? = null
    private var layout_titleBar_rootLayout: RelativeLayout? = null
    constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs) {
        if (attrs != null) {
            initAttrs(context, attrs)
        }
        initView(context)
    }

    private fun initAttrs(context: Context, attrs: AttributeSet) {
        var attributeSet = context.obtainStyledAttributes(attrs, R.styleable.TitleBar)
        titleColor = attributeSet.getColor(R.styleable.TitleBar_title_text_color, Color.BLUE)
        titleBg = attributeSet.getColor(R.styleable.TitleBar_title_bg, Color.WHITE)
        titleContent = attributeSet.getString(R.styleable.TitleBar_title_content)
        //释放
        attributeSet.recycle()
    }

    private fun initView(context: Context) {

        val view = LayoutInflater.from(context).inflate(R.layout.title_bar_layout, this, true)
        iv_titleBar_left = view.findViewById(R.id.titleBar_back)
        iv_titleBar_right = view.findViewById(R.id.iv_titlebar_right)
        tv_titleBar_title = view.findViewById(R.id.titleBar_title)
        layout_titleBar_rootLayout = view.findViewById(R.id.layout_titleBar_rootLayout)
        //设置背景颜色
        layout_titleBar_rootLayout?.setBackgroundColor(titleBg ?: 0)
        tv_titleBar_title?.setTextColor(titleColor ?: 0)
        setTitle(titleContent)
    }

    private fun setTitle(title: String?){
        if(!TextUtils.isEmpty(title)){
            tv_titleBar_title?.text = title
        }
    }

    fun setLeftListener(onClickListener: OnClickListener?) {
        iv_titleBar_left?.setOnClickListener(onClickListener)
    }

    fun setRightListener(onClickListener: OnClickListener?) {
        iv_titleBar_right?.setOnClickListener(onClickListener)
    }
}