package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pivot(
    @SerialName("doctor_id") val doctorId: Long,
    @SerialName("qualification_id") val qualificationId: Long
)