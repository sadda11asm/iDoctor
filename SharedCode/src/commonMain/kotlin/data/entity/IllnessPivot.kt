package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IllnessPivot(
    @SerialName("doctor_id") val doctorId: Long,
    @SerialName("illness_id") val illnessId: Long
)