package com.example.mppapp.model

import org.kotlin.mpp.mobile.data.entity.Skill
import java.io.Serializable


data class SkillO(
    val name: String,
    val id: Long,
    val pivot: SkillPivotO
):Serializable

fun Skill.to() = SkillO(name, id, pivot.to())