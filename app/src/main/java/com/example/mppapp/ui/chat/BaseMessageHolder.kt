package com.example.mppapp.ui.chat

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mppapp.model.ViewModel

abstract class BaseMessageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    open fun bind(item: ViewModel) {}
}