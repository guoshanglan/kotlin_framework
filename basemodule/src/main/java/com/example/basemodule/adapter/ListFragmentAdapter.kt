package com.example.basemodule.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Viewpage
 * @author gukaihong
 * @date 2020/5/9
 */
class ListFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val listFragment: MutableList<Fragment>) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return listFragment.size
    }

    override fun createFragment(position: Int) = listFragment[position]


}