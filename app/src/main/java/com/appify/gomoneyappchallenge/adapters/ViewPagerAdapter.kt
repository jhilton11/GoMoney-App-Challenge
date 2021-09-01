package com.appify.gomoneyappchallenge.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.appify.gomoneyappchallenge.fragments.FixturesFragment
import com.appify.gomoneyappchallenge.fragments.StandingsFragment
import com.appify.gomoneyappchallenge.fragments.TeamsFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return StandingsFragment()
            1 -> return FixturesFragment()
        }
        return TeamsFragment()
    }
}