package org.kotlin.mpp.mobile.presentation.main

import org.kotlin.mpp.mobile.presentation.BasePresenter
import org.kotlin.mpp.mobile.presentation.BaseView

interface MainContract {

    interface View : BaseView<Presenter> {
        val isActive: Boolean

        fun setProgressIndicator(active: Boolean)

        fun showStatistics(numberOfIncompleteTasks: Int, numberOfCompletedTasks: Int)

        fun showLoadingStatisticsError()
    }

    interface Presenter : BasePresenter
}