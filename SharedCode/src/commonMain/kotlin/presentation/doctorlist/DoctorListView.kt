package org.kotlin.mpp.mobile.presentation.doctorlist

import org.kotlin.mpp.mobile.data.entity.DoctorResponse

interface DoctorListView {

    fun token(): String

    fun showDoctors(doctorResponse: DoctorResponse)

    fun showMoreDoctors(doctorResponse: DoctorResponse)

    fun showNoDoctors()

    fun showLoadFailed()
}