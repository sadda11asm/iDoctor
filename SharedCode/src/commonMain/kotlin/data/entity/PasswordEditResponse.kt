package org.kotlin.mpp.mobile.data.entity

import data.entity.UserFull
import kotlinx.serialization.Serializable

@Serializable
data class PasswordEditResponse(
    val data: UserFull?,
    val message: String? = null,
    val errors: CurrentPasswordError? = null

)

@Serializable
data class CurrentPasswordError(
    val password: List<String>? = emptyList(),
    val current_password: List<String>? = emptyList()
)
