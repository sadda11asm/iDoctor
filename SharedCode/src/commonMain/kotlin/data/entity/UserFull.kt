package data.entity

import db.UserModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserFull(
    val id: Int,
    @SerialName("firstname") var firstName: String,
    @SerialName("lastname") var lastName: String,
    var patronymic: String?,
    var phone: String,
    var email: String,
    @SerialName("created_at") var createdAt: EventDate?,
    @SerialName("updated_at") var updatedAt: EventDate?,
    @SerialName("doctor_id") var doctorId: String? = null,
    var birthday: String? = null
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
        doctor_id
    )
}
