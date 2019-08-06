package com.example.mppapp.model

import com.example.mppapp.R

class DateViewModel(val formattedDate: String) : ViewModel() {
    override fun type() = R.layout.item_date_message
}