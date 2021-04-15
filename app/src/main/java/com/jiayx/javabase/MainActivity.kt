package com.jiayx.javabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.*
import com.jiayx.javabase.canvas.ui.fragment.*
import com.jiayx.javabase.canvas.view.CustomViewGroup
import com.jiayx.javabase.canvas.viewpage.CollectionAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewPage2 : ViewPager2
    private lateinit var adapter: CollectionAdapter
    private lateinit var tabLayout: TabLayout
    private val fragments by lazy {
       listOf(
           AnimateFragment(),
          CircleFragment(),
          GridFragment(),
          RectFragment(),
          PointFragment(),
          CenterCircleFragment(),
          LinearGradientFragment(),
          RadialGradientFragment(),
          SweepGradientFragment(),
          ClipRectFragment(),
           AppEditTextFragment(),
           CustomViewGroupFragment()

       )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPage2 = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tab_layout)
        adapter = CollectionAdapter(this,fragments)
        viewPage2.adapter = adapter
        val array = arrayOf("动画","自定义圆","自定义网格","自定义矩形","自定义圆点","绘制圆心坐标圆"
            ,"线性渐变","辐射渐变","扫描渐变","裁切","AppEdit","自定义V")
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = array[position]
        }.attach()
    }
}