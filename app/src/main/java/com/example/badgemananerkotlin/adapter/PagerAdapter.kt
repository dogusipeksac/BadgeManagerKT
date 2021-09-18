package com.example.badgemananerkotlin.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.badgemananerkotlin.fragment.GridFragment


class PagerAdapter(fm: FragmentManager?, private val fragments: List<GridFragment>) :
    FragmentPagerAdapter(fm)  {
    override fun getItem(position: Int): Fragment? {
        return fragments[position] as Fragment
    }

    override fun getCount(): Int {
        return fragments.size
    }
}


