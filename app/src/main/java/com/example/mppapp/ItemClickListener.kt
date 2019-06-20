package com.example.mppapp

interface ItemClickListener<T> {

    fun onClick(data: T)
}