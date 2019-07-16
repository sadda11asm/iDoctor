package data.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val data: UserFull
)