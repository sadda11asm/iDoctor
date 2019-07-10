package org.kotlin.mpp.mobile.presentation.chat

import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.data.entity.ChatFullRequest
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.domain.usecases.GetChatFull
import org.kotlin.mpp.mobile.presentation.BasePresenter
import kotlin.coroutines.CoroutineContext

class ChatPresenter(
    private val getChatFull: GetChatFull,
    private val coroutineContext: CoroutineContext = defaultDispatcher
) : BasePresenter<ChatView>(coroutineContext) {

    private lateinit var token: String

    override fun onViewAttached(view: ChatView) {
        super.onViewAttached(view)
        token = view.token()
        loadChat()
    }

    fun loadChat() {
        scope.launch {
            getChatFull(
                params = ChatFullRequest(token, 5), // TODO change hardcoded chatId
                onSuccess = { view?.showChat(it) },
                onFailure = { view?.showError(it) } // TODO handle failure
            )
        }
    }
}