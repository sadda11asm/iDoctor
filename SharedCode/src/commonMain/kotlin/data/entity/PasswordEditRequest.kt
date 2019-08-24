package org.kotlin.mpp.mobile.data.entity

import data.entity.UserFull
import kotlinx.serialization.Serializable


@Serializable
data class PasswordEditRequest(
    val token: String,
    val userId: Int,
    val currentPassword: String,
    val password: String,
    val confirmPassword: String
)