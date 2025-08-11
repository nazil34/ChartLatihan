package com.example.chartlatihan

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ChartPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        return if(position == 0) MingguanFragment() else BulananFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }

}