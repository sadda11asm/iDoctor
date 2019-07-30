package org.kotlin.mpp.mobile.domain.usecases

import UseCase
import data.api.DoctorApi
import org.kotlin.mpp.mobile.data.entity.DoctorRequest
import org.kotlin.mpp.mobile.data.entity.DoctorResponse
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success

class GetDoctors(private val doctorApi: DoctorApi) : UseCase<DoctorResponse, DoctorRequest>() {
    override suspend fun run(params: DoctorRequest): Either<Exception, DoctorResponse> {
        return try {
            val response = doctorApi.getDoctors(params.token, params.page)
            Success(response)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}