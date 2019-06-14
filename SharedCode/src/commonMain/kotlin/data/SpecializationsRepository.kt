package data

import domain.Specialization


class SpecializationsRepository(
    private val specializationSource: SpecializationSource
) {

    suspend fun getSpecializationsList(): List<Specialization> = specializationSource.getSpecializationsList()
}

interface SpecializationSource {
    suspend fun getSpecializationsList(): List<Specialization>
}
