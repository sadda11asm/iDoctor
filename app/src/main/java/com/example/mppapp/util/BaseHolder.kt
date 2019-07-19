package com.example.mppapp.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    open fun bind(position: Int) {}
}