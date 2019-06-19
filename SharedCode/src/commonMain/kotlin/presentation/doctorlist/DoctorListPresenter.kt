package org.kotlin.mpp.mobile.presentation.doctorlist

import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.data.entity.DoctorResponse
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.domain.usecases.GetDoctors
import org.kotlin.mpp.mobile.presentation.BasePresenter
import org.kotlin.mpp.mobile.presentation.login.LoginView
import kotlin.coroutines.CoroutineContext

//TODO change third parameter
class DoctorListPresenter(
    private val getDoctors: GetDoctors,
    private val coroutineContext: CoroutineContext = defaultDispatcher,
    private val token: String = ""
): BasePresenter<DoctorListView>(coroutineContext) {

    fun onLoadDoctors() {
        scope.launch {
            getDoctors(
                params = token,
                onSuccess = { view?.showDoctors(it) },
                onFailure = { view?.showLoadFailed(it) }
            )
        }
    }
}

interface DoctorListView {
    fun showDoctors(doctorResponse: DoctorResponse)
    fun showLoadFailed(e: Exception)
}