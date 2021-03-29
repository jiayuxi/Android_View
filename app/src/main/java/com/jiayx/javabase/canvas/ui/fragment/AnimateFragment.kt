package com.jiayx.javabase.canvas.ui.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.jiayx.javabase.R

/**
 *Created by yuxi_
on 2021/3/29
 */
class AnimateFragment : Fragment() {

     private var handler = Handler()
      override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
              return inflater.inflate(R.layout.animate_layout,container,false)
          }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = view.findViewById(R.id.animate_image) as ImageView
        val btn = view.findViewById(R.id.start_animate) as Button
       /* handler.postAtTime(Runnable {
            imageView.animate().translationXBy(500f).duration = 500
        },2000)*/

       var objectAnimator =  ObjectAnimator.ofFloat(imageView,"translationX",500f)
        objectAnimator.duration = 2000

        btn.setOnClickListener {
            objectAnimator.start()
        }

    }
}