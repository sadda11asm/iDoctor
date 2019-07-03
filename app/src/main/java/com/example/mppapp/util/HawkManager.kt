package com.example.mppapp.util

import com.orhanobut.hawk.Hawk


private const val ACCESS_TOKEN = "access_token"

fun getAccessToken() : String = Hawk.get<String>(ACCESS_TOKEN)

