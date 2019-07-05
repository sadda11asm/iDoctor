package org.kotlin.mpp.mobile.presentation.doctorlist

import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.data.entity.DoctorRequest
import org.kotlin.mpp.mobile.data.entity.DoctorResponse
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.domain.usecases.GetDoctors
import org.kotlin.mpp.mobile.presentation.BasePresenter
import kotlin.coroutines.CoroutineContext

class DoctorListPresenter(
    private val getDoctors: GetDoctors,
    private val coroutineContext: CoroutineContext = defaultDispatcher
) : BasePresenter<DoctorListView>(coroutineContext) {

    private lateinit var token : String

    private var isFirstLoad = true

    private var page = 1

    override fun onViewAttached(view: DoctorListView) {
        super.onViewAttached(view)
        token = view.token()
        loadDoctors()
    }

    fun loadDoctors() {
        scope.launch {
            getDoctors(
                params = DoctorRequest(token, page),
                onSuccess = { if (isFirstLoad) view?.showDoctors(it) else view?.showMoreDoctors(it) },
                onFailure = { view?.showLoadFailed(it) }
            )
            isFirstLoad = false
            page++
        }
    }

    fun refreshDoctors() {
        isFirstLoad = true
        page = 1
        loadDoctors()
    }
}

interface DoctorListView {

    fun token(): String

    fun showDoctors(doctorResponse: DoctorResponse)

    fun showMoreDoctors(doctorResponse: DoctorResponse)

    fun showLoadFailed(e: Exception)
}