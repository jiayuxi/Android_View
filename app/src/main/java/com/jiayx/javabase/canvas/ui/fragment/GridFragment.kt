package com.jiayx.javabase.canvas.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jiayx.javabase.R

/**
 *Created by yuxi_
on 2021/3/24
 */
class GridFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.grid_canvar_layout, container, false)
    }
}