package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SkillPivot(
    @SerialName("doctor_id") val doctorId: Long,
    @SerialName("skill_id") val skillId: Long,
    var weight: Long
)