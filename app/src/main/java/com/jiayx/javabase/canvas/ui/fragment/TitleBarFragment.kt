package com.jiayx.javabase.canvas.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.jiayx.javabase.R
import kotlinx.android.synthetic.main.title_bar_fragment_layout.*

/**
 *Created by yuxi_
on 2021/4/26
 */
class TitleBarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.title_bar_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var tileBar = view.findViewById<View>(R.id.titleBar)
        var content : TextView = view.findViewById<View>(R.id.titleBarContent) as TextView
        titleBar.setLeftListener {
            content.text = "点击左按钮"
        }
        titleBar.setRightListener{
            content.text = "点击右按钮"
        }
    }
}