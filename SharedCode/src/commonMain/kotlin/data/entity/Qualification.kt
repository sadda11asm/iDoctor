package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Qualification(
    var name: String,
    val id: Long,
    val pivot: Pivot
)