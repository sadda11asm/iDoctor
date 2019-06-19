package org.kotlin.mpp.mobile.presentation

import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.data.entity.Doctor
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.domain.usecases.GetDoctors
import kotlin.coroutines.CoroutineContext

class MainPresenter(
    coroutineContext: CoroutineContext = defaultDispatcher,
    private val getDoctors: GetDoctors
): BasePresenter<DoctorsView>(coroutineContext) {
    override fun onViewAttached(view: DoctorsView) {
        view.setLoadingVisible(true)
        getDoctors()
    }

    private fun getDoctors() {
        scope.launch {
            getDoctors(
                "",
                onSuccess = { view?.setDoctors(it.data[0]) },
                onFailure = { view?.showDoctorsFailedToLoad() }
            )
            view?.setLoadingVisible(false)
        }
    }
}

interface DoctorsView {

    fun setDoctors(doctor: Doctor)

    fun showDoctorsFailedToLoad()

    fun setLoadingVisible(visible: Boolean)
}