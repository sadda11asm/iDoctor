package org.kotlin.mpp.mobile.data.entity

import data.entity.UserFull

data class UserEditRequest(
    val token: String,
    val user: UserFull
)