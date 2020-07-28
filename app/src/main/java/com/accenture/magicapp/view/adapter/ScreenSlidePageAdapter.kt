package com.accenture.magicapp.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.accenture.magicapp.Util.Common.PAGES.NUM_PAGES
import com.accenture.magicapp.view.fragment.ScreenSlidePageFragment

class ScreenSlidePageAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    override fun createFragment(position: Int): Fragment = ScreenSlidePageFragment()
}