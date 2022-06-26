package com.android.example.pecodetestapp.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.example.pecodetestapp.ui.notification.NotificationFragment

class SlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private var currentCount = 0

    fun setItemCount(count: Int) {
        currentCount = count
    }

    override fun getItemCount(): Int = currentCount

    override fun createFragment(position: Int): Fragment = NotificationFragment.newInstance(position)
}