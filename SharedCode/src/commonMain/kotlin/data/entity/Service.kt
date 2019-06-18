package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Service(
    val name: String,
    val id: Long,
    val pivot: ServicePivot
)