package com.example.mppapp.util

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(
    private var list: MutableList<T>
) : RecyclerView.Adapter<BaseHolder>() {

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.bind(position)
    }
}