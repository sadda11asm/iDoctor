package org.kotlin.mpp.mobile.presentation.chatlist

import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.data.entity.DoctorResponse
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.domain.usecases.GetChatList
import org.kotlin.mpp.mobile.domain.usecases.GetDoctors
import org.kotlin.mpp.mobile.presentation.BasePresenter
import org.kotlin.mpp.mobile.presentation.doctorlist.DoctorListView
import kotlin.coroutines.CoroutineContext

class ChatListPresenter(
    private val getChatList: GetChatList,
    private val coroutineContext: CoroutineContext = defaultDispatcher
) : BasePresenter<ChatListView>(coroutineContext) {

    override fun onViewAttached(view: ChatListView) {
        super.onViewAttached(view)
        view.showLoading()
    }

    fun onLoadDoctors(token: String) {
        scope.launch {
            getChatList(
                params = token,
                onSuccess = { view?.showChats(it) },
                onFailure = { view?.showLoadFailed(it) }
            )
        }
    }
}

interface ChatListView {
    fun showLoading() // TODO add boolean loading param
    fun showChats (doctorResponse: DoctorResponse)
    fun showLoadFailed(e: Exception)
}