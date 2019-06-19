package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    val password: String
)