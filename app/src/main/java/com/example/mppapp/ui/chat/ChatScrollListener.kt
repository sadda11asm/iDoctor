package com.example.mppapp.ui.chat

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class ChatScrollListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
        val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
        val totalItemCount = layoutManager.itemCount

        changeFabState(lastVisibleItem != totalItemCount - 1)
        showDateViewHolder(firstVisibleItem)
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        if(newState == RecyclerView.SCROLL_STATE_IDLE) {
            hideDateViewHolder()
        }
    }

    abstract fun changeFabState(isNotLastItem: Boolean)

    abstract fun showDateViewHolder(firstVisibleItem: Int)

    abstract fun hideDateViewHolder()
}