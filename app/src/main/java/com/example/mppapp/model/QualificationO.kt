package com.example.mppapp.model

import org.kotlin.mpp.mobile.data.entity.Qualification
import java.io.Serializable


data class QualificationO(
    var name: String,
    val id: Long,
    val pivot: PivotO
):Serializable

fun Qualification.to() = QualificationO(name, id, pivot.to())