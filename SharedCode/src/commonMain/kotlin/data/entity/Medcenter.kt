package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Medcenter(
    val id: Int,
    val name: String
)