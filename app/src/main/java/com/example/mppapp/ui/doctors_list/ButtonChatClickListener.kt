package com.example.mppapp.ui.doctors_list

interface ButtonChatClickListener<T> {

    /**
     * This interface is used to listen to button click event
     * inside RecyclerView ViewHolder
     */

    fun onChatButtonClick(item: T, position: Int)
}