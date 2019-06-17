package domain

import kotlinx.serialization.Serializable

@Serializable
data class Illness(
    val name: String,
    val symptoms: List<Symptom>
)