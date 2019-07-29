package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AuthorizationRequest(
    @SerialName("client_id") val clientId: Int,
    val username: String,
    val password: String,
    @SerialName("grant_type") val grantType: String = "password",
    val scope: String = "*"
)