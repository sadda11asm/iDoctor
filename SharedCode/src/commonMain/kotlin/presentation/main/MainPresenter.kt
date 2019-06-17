package org.kotlin.mpp.mobile.presentation

import org.kotlin.mpp.mobile.presentation.main.MainContract
import org.kotlin.mpp.mobile.usecases.GetDoctors

class MainPresenter(
    private var view: MainContract.View,
    private val getDoctors: GetDoctors
): MainContract.Presenter {
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    init {
        view.presenter = this
    }

//    fun onCreate() = launch(UI) {
//        val locations = bg { getLocations() }.await()
//        view?.renderLocations(locations)
//    }
//
//    fun newLocationClicked() = launch(UI) {
//        val locations = bg { requestNewLocation() }.await()
//        view?.renderLocations(locations)
//    }

//    fun onDestroy() {
//        view = null
//    }
}