package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Illness(
    val name: String,
    val id: Long,
    val pivot: IllnessPivot
)