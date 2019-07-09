package com.example.mppapp.util

import com.orhanobut.hawk.Hawk
import org.kotlin.mpp.mobile.data.entity.AuthorizationResponse


private const val ACCESS_TOKEN = "access_token"

private const val REFRESH_TOKEN = "refresh_token"

private const val EXPIRES_IN = "expires_in"


fun getAccessToken() : String = Hawk.get<String>(ACCESS_TOKEN)

fun isTokenPresent() = Hawk.contains(ACCESS_TOKEN)

fun putAuthResponse(response: AuthorizationResponse) = with(response) {
    Hawk.put(ACCESS_TOKEN, access_token)
    Hawk.put(REFRESH_TOKEN, refresh_token)
    Hawk.put(EXPIRES_IN, expires_in)
}


