package domain

import kotlinx.serialization.Serializable

@Serializable
data class Specialization(
    val name: String,
    val illnesses: List<Illness>
)