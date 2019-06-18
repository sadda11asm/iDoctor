package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Skill(
    val name: String,
    val id: Long,
    val pivot: SkillPivot
)