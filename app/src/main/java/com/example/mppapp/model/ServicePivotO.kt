package com.example.mppapp.model

import org.kotlin.mpp.mobile.data.entity.ServicePivot
import java.io.Serializable


data class ServicePivotO(
    val doctorId: Long,
    val serviceId: Long,
    val price: Long = 0
):Serializable

fun ServicePivot.to() = ServicePivotO(doctorId, serviceId, price)