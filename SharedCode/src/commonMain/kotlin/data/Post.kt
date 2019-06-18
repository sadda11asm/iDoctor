package org.kotlin.mpp.mobile.data

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val userId: Long,
    val id: Long,
    var title: String,
    var body: String
)