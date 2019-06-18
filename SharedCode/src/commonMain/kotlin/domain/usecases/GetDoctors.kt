package org.kotlin.mpp.mobile.usecases

import domain.Specialization
import org.kotlin.mpp.mobile.data.DoctorApi
import org.kotlin.mpp.mobile.domain.model.Doctor
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success
import UseCase

class GetDoctors (private val doctorApi: DoctorApi): UseCase<Doctor, UseCase.None> {
    override suspend fun run(params: UseCase.None): Either<Exception, Doctor> {
        return try {
            val movies = doctorApi.getDoctors().toModel()
            Success(movies)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}