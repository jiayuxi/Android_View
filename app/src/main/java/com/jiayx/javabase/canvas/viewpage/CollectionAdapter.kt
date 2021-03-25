package com.jiayx.javabase.canvas.viewpage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *Created by yuxi_
on 2021/3/24
 */
class CollectionAdapter(fragmentActivity: FragmentActivity,private val fragments : List<Fragment>) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment  = fragments[position]
}