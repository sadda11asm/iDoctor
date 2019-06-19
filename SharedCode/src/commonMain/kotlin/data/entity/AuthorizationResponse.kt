package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.Serializable


@Serializable
data class AuthorizationResponse(
    val token_type: String,
    val expires_in: Long,
    val access_token: String,
    val refresh_token: String
)