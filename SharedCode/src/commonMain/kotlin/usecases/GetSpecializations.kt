package org.kotlin.mpp.mobile.usecases

import data.SpecializationsRepository
import domain.Specialization

class getSpecializations (private val specializationsRepository: SpecializationsRepository) {
    suspend operator fun invoke(): List<Specialization> =
        specializationsRepository.getSpecializationsList()
}