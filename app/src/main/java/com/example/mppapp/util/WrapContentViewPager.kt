package com.example.mppapp.util

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.View.MeasureSpec
import androidx.viewpager.widget.ViewPager


class WrapContentViewPager : ViewPager {

    /**
     * Constructor
     *
     * @param context the context
     */
    constructor(context: Context) : super(context) {}

    /**
     * Constructor
     *
     * @param context the context
     * @param attrs the attribute set
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightMeasureSpec = heightMeasureSpec

        var height = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
            val h = child.measuredHeight
            if (h > height) height = h
        }

        if (height != 0) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    /**
     * Determines the height of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @param view the base view with already measured height
     *
     * @return The height of the view, honoring constraints from measureSpec
     */
//    private fun measureHeight(measureSpec: Int, view: View?): Int {
//        var result = 0
//        val specMode = MeasureSpec.getMode(measureSpec)
//        val specSize = MeasureSpec.getSize(measureSpec)
//
//        if (specMode == MeasureSpec.EXACTLY) {
//            result = specSize
//        } else {
//            // set the height from the base view if available
//            if (view != null) {
//                result = view!!.getMeasuredHeight()
//            }
//            if (specMode == MeasureSpec.AT_MOST) {
//                result = Math.min(result, specSize)
//            }
//        }
//        return result
//    }

}