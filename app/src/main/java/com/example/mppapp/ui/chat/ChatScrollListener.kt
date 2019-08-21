package com.example.mppapp.ui.chat

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class ChatScrollListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    private var firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

    private var lastVisibleItem = layoutManager.findLastVisibleItemPosition()

    private var totalItemCount = layoutManager.itemCount

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
        lastVisibleItem = layoutManager.findLastVisibleItemPosition()
        totalItemCount = layoutManager.itemCount

        changeFabState(lastVisibleItem != totalItemCount - 1)

        if(isDateStateChangeable()) {
            showDateViewHolder(firstVisibleItem)
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        if(newState == RecyclerView.SCROLL_STATE_IDLE) {
            hideDateViewHolder()
        }
    }

    private fun isDateStateChangeable(): Boolean {
        return firstVisibleItem != 0 && lastVisibleItem != totalItemCount - 1
    }

    abstract fun changeFabState(isNotLastItem: Boolean)

    abstract fun showDateViewHolder(firstVisibleItem: Int)

    abstract fun hideDateViewHolder()
}