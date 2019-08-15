package data.entity

import db.UserModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserFull(
    val id: Int,
    @SerialName("firstname") val firstName: String,
    @SerialName("lastname") val lastName: String,
    val patronymic: String?,
    val phone: String,
    val email: String,
    @SerialName("created_at") val createdAt: EventDate?,
    @SerialName("updated_at") val updatedAt: EventDate?,
    @SerialName("verified_recently") val verified: Boolean,
    @SerialName("doctor_id") val doctorId: String? = null,
    val birthday: String? = null
    )

fun UserModel.to(): UserFull {
    return UserFull(
        id.toInt(),
        firstname,
        lastname,
        patronymic,
        phone,
        email,
        created_at,
        updated_at,
        verified.toInt() == 1,
        doctor_id
    )
}
