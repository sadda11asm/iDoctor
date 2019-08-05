package com.example.mppapp.ui.chat

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class FabScrollListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
        val totalItemCount = layoutManager.itemCount

        changeFabState(lastVisibleItem != totalItemCount - 1)
    }

    abstract fun changeFabState(isNotLastItem: Boolean)
}