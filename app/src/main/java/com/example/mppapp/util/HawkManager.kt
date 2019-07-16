package com.example.mppapp.util

import com.orhanobut.hawk.Hawk
import data.entity.UserFull
import org.kotlin.mpp.mobile.data.entity.AuthorizationResponse


private const val ACCESS_TOKEN = "access_token"

private const val REFRESH_TOKEN = "refresh_token"

private const val EXPIRES_IN = "expires_in"

private const val USER_ID = "user_id"

private const val NAME = "name"

private const val IS_DOCTOR = "is_doctor"


fun getAccessToken() : String = Hawk.get<String>(ACCESS_TOKEN)

fun isTokenPresent() = Hawk.contains(ACCESS_TOKEN)

fun putAuthResponse(response: AuthorizationResponse) = with(response) {
    Hawk.put(ACCESS_TOKEN, access_token)
    Hawk.put(REFRESH_TOKEN, refresh_token)
    Hawk.put(EXPIRES_IN, expires_in)
}

fun putUser(user: UserFull) = with(user) {
    Hawk.put(USER_ID, id)
    Hawk.put(NAME, "$firstname $lastname")
    Hawk.put(IS_DOCTOR, doctorId!=null)
}

fun getUserId(): Int = Hawk.get<Int>(USER_ID)

fun getName(): String = Hawk.get<String>(NAME)

fun getIsDoctor(): Boolean = Hawk.get<Boolean>(IS_DOCTOR)