package data.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val data: UserFull
)

@Serializable
data class UserEditResponse(
    val success: String,
    val user: UserFull
)