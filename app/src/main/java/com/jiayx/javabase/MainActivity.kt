package com.jiayx.javabase

import android.graphics.PointF
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.*
import com.jiayx.javabase.canvas.ui.fragment.*
import com.jiayx.javabase.canvas.viewpage.CollectionAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewPage2 : ViewPager2
    private lateinit var adapter: CollectionAdapter
    private lateinit var list : ArrayList<Fragment>
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPage2 = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tab_layout)
        list = ArrayList<Fragment>()
        val circleFragment = CircleFragment()
        val gridFragment = GridFragment()
        val rectFragment = RectFragment()
        val pointFragment = PointFragment()
        val centerCircleFragment = CenterCircleFragment()
        val linearGradientFragment = LinearGradientFragment()
        list.add(circleFragment)
        list.add(gridFragment)
        list.add(rectFragment)
        list.add(pointFragment)
        list.add(centerCircleFragment)
        list.add(linearGradientFragment)
        adapter = CollectionAdapter(this,list)
        viewPage2.adapter = adapter

        val array = arrayOf("自定义圆","自定义网格","自定义矩形","自定义圆点","绘制圆心坐标圆","线性渐变")
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = array[position]
        }.attach()
    }
}