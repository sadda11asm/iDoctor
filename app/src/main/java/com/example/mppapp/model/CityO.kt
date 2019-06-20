package com.example.mppapp.model

import org.kotlin.mpp.mobile.data.entity.City
import java.io.Serializable


data class CityO(
    val name: String,
    val id: Long,
    var href: String
):Serializable

fun City.to() = CityO(
    name, id, href
)