package org.kotlin.mpp.mobile.usecases

import domain.Specialization
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success
import UseCase
import data.DoctorApi
import org.kotlin.mpp.mobile.data.entity.Doctor

class GetDoctors (private val doctorApi: DoctorApi): UseCase<Doctor, UseCase.None>() {
    override suspend fun run(params: UseCase.None): Either<Exception, Doctor> {
        return try {
            val doctor = doctorApi.getDoctors()
            Success(doctor.doctor)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}