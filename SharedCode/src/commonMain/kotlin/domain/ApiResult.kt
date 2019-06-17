package org.kotlin.mpp.mobile.domain

import domain.Specialization
import kotlinx.serialization.Serializable

@Serializable
data class ApiResult(
//    val `data`: List<Data>
    val userId: String,
    val id: String
)

@Serializable
data class Data (
    val images: Specialization
)
