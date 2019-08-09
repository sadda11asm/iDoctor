package org.kotlin.mpp.mobile.domain.usecases
import UseCase
import data.api.DoctorApi
import org.kotlin.mpp.mobile.data.entity.Doctor
import org.kotlin.mpp.mobile.data.entity.DoctorResponse
import org.kotlin.mpp.mobile.data.entity.ScheduleRequest
import org.kotlin.mpp.mobile.data.entity.ScheduleResponse
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success

class GetSchedule(private val doctorApi: DoctorApi): UseCase<ScheduleResponse, ScheduleRequest>() {
    override suspend fun run(params: ScheduleRequest): Either<Exception, ScheduleResponse> {
        return try{
            val response = doctorApi.getSchedule(params)
            Success(response)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}