package com.example.mppapp.util

interface ButtonChatClickListener<T> {

    /**
     * This interface is used to listen to button click event
     * inside RecyclerView ViewHolder
     */

    fun onButtonClick(item: T, position: Int)
}