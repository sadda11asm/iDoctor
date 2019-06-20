package com.example.mppapp.model

import org.kotlin.mpp.mobile.data.entity.Illness
import java.io.Serializable

data class IllnessO(
    val name: String,
    val id: Long,
    val pivot: IllnessPivotO
): Serializable

fun Illness.to() = IllnessO(name, id, pivot.to())