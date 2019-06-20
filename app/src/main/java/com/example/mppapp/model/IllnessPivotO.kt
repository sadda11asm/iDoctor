package com.example.mppapp.model

import org.kotlin.mpp.mobile.data.entity.IllnessPivot
import java.io.Serializable


data class IllnessPivotO(
    val doctorId: Long,
    val illnessId: Long
):Serializable

fun IllnessPivot.to() = IllnessPivotO(doctorId, illnessId)