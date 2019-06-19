package org.kotlin.mpp.mobile.domain.usecases

import UseCase
import data.DoctorApi
import org.kotlin.mpp.mobile.data.entity.DoctorResponse
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success

class GetDoctors(private val doctorApi: DoctorApi): UseCase<DoctorResponse, String>() {
    override suspend fun run(params: String): Either<Exception, DoctorResponse> {
        return try {
            val response = doctorApi.getDoctors()
            Success(response)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}

