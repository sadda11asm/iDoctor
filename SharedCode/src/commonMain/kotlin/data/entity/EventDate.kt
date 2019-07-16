package data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventDate (
    val date: String,
    @SerialName("timezone_type") val timezoneType: Int,
    val timezone: String

)