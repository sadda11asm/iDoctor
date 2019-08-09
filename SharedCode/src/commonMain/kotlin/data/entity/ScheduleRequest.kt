package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleRequest(
    val id: Int,
    val token: String
)