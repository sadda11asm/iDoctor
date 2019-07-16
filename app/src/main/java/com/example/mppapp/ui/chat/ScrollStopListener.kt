package com.example.mppapp.ui.chat

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class ScrollStopListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        if(newState == RecyclerView.SCROLL_STATE_IDLE) {
            val position = layoutManager.findLastCompletelyVisibleItemPosition()
            performAction(position)
        }
    }

    abstract fun performAction(position: Int)
}