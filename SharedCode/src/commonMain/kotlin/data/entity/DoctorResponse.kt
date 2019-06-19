package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class DoctorResponse(
    val data: List<Doctor>
)