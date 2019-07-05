package com.example.mppapp.ui.doctors_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    open fun bind(position: Int) {}
}