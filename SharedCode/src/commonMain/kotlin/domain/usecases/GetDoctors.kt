package org.kotlin.mpp.mobile.usecases

import domain.Specialization

class GetDoctors (private val specializationsRepository: SpecializationsRepository) {
    suspend operator fun invoke(): Specialization =
        specializationsRepository.getSpecializationsList()
}