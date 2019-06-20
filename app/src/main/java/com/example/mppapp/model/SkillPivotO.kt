package com.example.mppapp.model

import org.kotlin.mpp.mobile.data.entity.SkillPivot
import java.io.Serializable


data class SkillPivotO(
    val doctorId: Long,
    val skillId: Long,
    var weight: Long
): Serializable

fun SkillPivot.to() = SkillPivotO(
    doctorId, skillId, weight
)