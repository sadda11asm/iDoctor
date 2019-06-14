package domain

data class Illness(
    val name: String,
    val symptoms: List<Symptom>
)