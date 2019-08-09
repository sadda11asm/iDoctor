package com.example.mppapp.model

import org.kotlin.mpp.mobile.data.entity.Service
import java.io.Serializable


data class ServiceO(
    val name: String,
    val id: Long,
    val pivot: ServicePivotO
): Serializable

fun Service.to() = ServiceO(name, id, pivot.to())