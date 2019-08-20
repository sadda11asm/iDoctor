package com.example.mppapp.util

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class CustomViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    var isSwipePageChangeEnabled = false

    /**
     * @property isSwipePageChangeEnabled defines whether there is a transition animation when
     * changing the page of viewPager
     */

    override fun onTouchEvent(ev: MotionEvent?) = false

    override fun onInterceptTouchEvent(ev: MotionEvent?) = isSwipePageChangeEnabled && super.onTouchEvent(ev)

    override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        super.setCurrentItem(item, isSwipePageChangeEnabled)
    }

    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item, isSwipePageChangeEnabled)
    }
}