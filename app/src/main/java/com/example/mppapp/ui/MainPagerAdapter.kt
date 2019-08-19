package com.example.mppapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mppapp.ui.chatlist.ChatListFragment
import com.example.mppapp.ui.doctors_list.DoctorListFragment
import com.example.mppapp.ui.profile.ProfileFragment
import java.lang.IllegalArgumentException

private const val NUM_OF_PAGES = 3

class MainPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> DoctorListFragment()
            1 -> ChatListFragment()
            2 -> ProfileFragment()
            else -> throw IllegalArgumentException()
        }
    }

    override fun getCount() = NUM_OF_PAGES
}