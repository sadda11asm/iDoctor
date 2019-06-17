package org.kotlin.mpp.mobile.data

data class Post(
    val userId: Long,
    val id: Long,
    var title: String,
    var body: String
)