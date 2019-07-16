package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServicePivot(
    @SerialName("doctor_id") val doctorId: Long,
    @SerialName("service_id") val serviceId: Long,
    val price: Long? = 0
)