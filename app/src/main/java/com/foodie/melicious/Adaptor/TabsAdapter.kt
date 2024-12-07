package com.foodie.melicious.Adaptor


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.foodie.melicious.fragments.CardFragment
import com.foodie.melicious.fragments.MpesaFragment

class TabsAdapter(fragmentActivity: Fragment) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CardFragment()
            1 -> MpesaFragment()
            else -> CardFragment()
        }
    }
}