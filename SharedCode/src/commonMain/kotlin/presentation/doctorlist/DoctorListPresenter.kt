package org.kotlin.mpp.mobile.presentation.doctorlist

import data.entity.CreateChatParams
import domain.usecases.CreateChat
import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.SocketListener
import org.kotlin.mpp.mobile.data.entity.Doctor
import org.kotlin.mpp.mobile.data.entity.DoctorRequest
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.domain.usecases.GetDoctors
import org.kotlin.mpp.mobile.presentation.BasePresenter
import org.kotlin.mpp.mobile.util.log
import kotlin.coroutines.CoroutineContext

class DoctorListPresenter(
    private val getDoctors: GetDoctors,
    private val createChat: CreateChat,
    private val coroutineContext: CoroutineContext = defaultDispatcher
) : BasePresenter<DoctorListView>(coroutineContext), SocketListener {

    private val token by lazy { view!!.token() }

    private var isFirstLoad = true

    private var page = 1

    fun start() {
        if (view!!.isConnectedToNetwork()) {
            loadDoctors()
        } else {
            view?.showNoConnection()
        }
    }

    fun loadDoctors() {
        scope.launch {
            getDoctors(
                params = DoctorRequest(token, page),
                onSuccess = { onDoctorsLoadSuccess(it.data) },
                onFailure = { onDoctorsLoadFailure() }
            )
        }
    }

    fun refreshDoctors() {
        page = 1
        scope.launch {
            getDoctors(
                params = DoctorRequest(token, page),
                onSuccess = { onDoctorsRefreshSuccess(it.data) },
                onFailure = { view?.showRefreshingFailed() }
            )
        }
    }

    fun createChat(
        token: String,
        title: String,
        userId: Int,
        anonymous: Boolean,
        doctorId: Int?,
        avatar: String,
        position: Int
    ) {
        view?.showChatCreateLoader(position)
        scope.launch {
            createChat(
                params = CreateChatParams(token, title, userId, anonymous, doctorId),
                onSuccess = { view?.openChat(it, avatar, title); view?.hideChatCreateLoader(position) },
                onFailure = { view?.showChatCreationError(it); view?.hideChatCreateLoader(position) }
            )
        }
    }

    private fun onDoctorsRefreshSuccess(doctors: MutableList<Doctor>) {
        view?.showRefreshedDoctors(doctors)
        isFirstLoad = false
        page++
    }

    private fun onDoctorsLoadSuccess(doctors: MutableList<Doctor>) {
        if (isFirstLoad) {
            view?.hideLoading()
            isFirstLoad = false
        } else {
            view?.hidePaging()
        }
        view?.showDoctors(doctors)
        page++
    }

    private fun onDoctorsLoadFailure() {
        if (isFirstLoad) {
            view?.showLoadingFailed()
        } else {
            view?.showPagingFailed()
        }
    }
}