package com.jiayx.javabase.canvas.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jiayx.javabase.R

/**
 *Created by yuxi_
on 2021/3/28
 */
class SweepGradientFragment : Fragment() {
    
      override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
              return inflater.inflate(R.layout.sweepgradient_layout,container,false)
          }
}