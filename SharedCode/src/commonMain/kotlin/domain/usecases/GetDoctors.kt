package org.kotlin.mpp.mobile.usecases

import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success
import UseCase
import data.DoctorApi
import org.kotlin.mpp.mobile.data.entity.DoctorResponse

class GetDoctors (private val doctorApi: DoctorApi): UseCase<DoctorResponse, UseCase.None>() {
    override suspend fun run(params: UseCase.None): Either<Exception, DoctorResponse> {
        return try {
            val doctorResponse = doctorApi.getDoctors()
            Success(doctorResponse)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}