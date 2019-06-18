package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class City(
    val name: String,
    val id: Long,
    var href: String
)