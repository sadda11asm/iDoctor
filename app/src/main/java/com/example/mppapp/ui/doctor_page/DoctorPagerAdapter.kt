package com.example.mppapp.ui.doctor_page

import android.content.Context
import android.provider.Settings.System.getString
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mppapp.R
import android.os.Bundle
import com.example.mppapp.model.ServiceO


class DoctorPagerAdapter (private val services: ArrayList<ServiceO>, private val id: Int, private val context: Context, fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
         return if (position == 0) {
             val bundle = Bundle()
             bundle.putInt("id", id)
             val scheduleFragment = ScheduleFragment()
             scheduleFragment.arguments = bundle
             scheduleFragment
        } else {
             val bundle = Bundle()
             bundle.putSerializable("services", services)
             val servicesFragment = ServicesFragment()
             servicesFragment.arguments = bundle
             servicesFragment
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) {
            context.getString(R.string.schedule_title)
        } else {
            context.getString(R.string.treatments_title)
        }
    }


}