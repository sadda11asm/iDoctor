package com.example.mppapp.model

import org.kotlin.mpp.mobile.data.entity.Pivot
import java.io.Serializable

data class PivotO(
    val doctorId: Long,
    val qualificationId: Long
):Serializable

fun Pivot.to() = PivotO(doctorId, qualificationId)