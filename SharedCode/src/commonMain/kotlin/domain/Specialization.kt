package domain

import kotlinx.serialization.Serializable

@Serializable
data class Specialization(
    val original: Original?
)


@Serializable
data class Original (
    val url: String
)
