package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Doctor(
    val id: Long,
    val name: String,
    var alias: String,
    @SerialName("user_id") val userId: Long?,
    var phone: String?,
    var email: String?,
    var avatar: String,
    var city: City,
    val qualifications: List<Qualification>,
    val skills: List<Skill>,
    @SerialName("works_since_year") val worksSinceYear: String,
    val illnesses: List<Illness>,
    val services: List<Service>?,
    var child: Int,
    @SerialName("child_min_age") var childMinAge: Int?,
    @SerialName("child_max_age") var childMaxAge: Int?,
    var ambulatory: Int,
    @SerialName("ambulatory_price") var ambulatoryPrice: String?,
    var price: Int?,
    @SerialName("showing_phone") var showingPhone: String?,
    @SerialName("show_phone") var showPhone: Int,
    var status: Int,
    @SerialName("avg_rate") var avgRate: Double,
    @SerialName("about_text") var aboutText: String?,
    @SerialName("treatment_text") var treatmentText: String?,
    @SerialName("grad_text") var gradText: String?,
    @SerialName("exp_text") var expText: String?,
    @SerialName("community_text") var communityText: String?,
    @SerialName("certs_text") var certsText: String?,
    @SerialName("feedback_link") var feedbackLink: String
)