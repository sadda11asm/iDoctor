package com.example.mppapp.model


import org.kotlin.mpp.mobile.data.entity.Doctor
import util.getYear
import java.io.Serializable

data class DoctorO(
    val id: Long,
    val name: String,
    var alias: String,
    val userId: Long?,
    var phone: String?,
    var email: String?,
    var avatar: String,
    var city: CityO?,
    val qualifications: List<QualificationO>,
    val skills: List<SkillO>,
    val worksSinceYear: String,
    val illnesses: List<IllnessO>,
    val services: List<ServiceO>?,
    var child: Int,
    var childMinAge: Int?,
    var childMaxAge: Int?,
    var ambulatory: Int,
    var ambulatoryPrice: String?,
    var price: Int?,
    var showingPhone: String?,
    var showPhone: Int,
    var status: Int,
    var avgRate: Double,
    var aboutText: String?,
    var treatmentText: String?,
    var gradText: String?,
    var expText: String?,
    var communityText: String?,
    var certsText: String?,
    var feedbackLink: String
) : Serializable {
    val experience: String
        get() = (getYear - worksSinceYear.toInt()).toString() + " лет"

    val specializations: String
        get() {

            var result = ""
            for (skill in skills) {
                result += skill.name
                result += ", "
            }
            if (skills.isEmpty()) return result
            return result.substring(0, result.length - 2)
        }

    val getIllnesses: String
        get() {
            var result = ""
            for (illness in illnesses) {
                result += illness.name
                result += ", "
            }
            if (illnesses.isEmpty()) return result
            return result.substring(0, result.length - 2)
        }

    val rating: Int
        get() = avgRate.toInt()

    val imageLink: String
        get() = "https://cabinet.idoctor.kz$avatar"

    val parsedTreatmentText: String
        get() {
            if (treatmentText == null) return "Услуги не указаны"
            return android.text.Html.fromHtml(treatmentText).toString()
        }

    val handledPhone: String
        get() {
            if (phone == null) {
                return "Телефон скрыт"
            } else {
                return phone.toString()
            }
        }

}

fun Doctor.to() = DoctorO(
    id,
    name,
    alias,
    userId,
    phone,
    email,
    avatar,
    city?.to(),
    qualifications.map { it.to() },
    skills.map { it.to() },
    worksSinceYear,
    illnesses.map { it.to() },
    services?.map { it.to() },
    child,
    childMinAge,
    childMaxAge,
    ambulatory,
    ambulatoryPrice,
    price,
    showingPhone,
    showPhone,
    status,
    avgRate,
    aboutText,
    treatmentText,
    gradText,
    expText,
    communityText,
    certsText,
    feedbackLink
)
