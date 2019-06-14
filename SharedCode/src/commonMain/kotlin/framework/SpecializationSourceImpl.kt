package framework

import data.SpecializationSource
import domain.Specialization
import io.ktor.client.HttpClient
import org.kotlin.mpp.mobile.data.api.SpecializationApi

class SpecializationSourceImpl(
    private val specializationApi: SpecializationApi
): SpecializationSource {
    override suspend fun getSpecializationsList(): List<Specialization> {
        var list : List<Specialization>  = emptyList()
        specializationApi.fetchSpecializations {
            list = it
        }
        return list
    }

}