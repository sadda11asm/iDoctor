package com.example.mppapp.ui.doctors_list

enum class ViewType {

    DOCTOR {
        override fun type() = 0

    },
    LOADER {
        override fun type() = 1
    };

    abstract fun type(): Int
}