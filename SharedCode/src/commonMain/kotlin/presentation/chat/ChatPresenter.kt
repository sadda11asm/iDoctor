package org.kotlin.mpp.mobile.presentation.chat

import data.entity.MarkMessageRequest
import data.entity.SendMessageRequest
import domain.usecases.MarkMessageAsRead
import domain.usecases.SendMessage
import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.data.entity.ChatFullRequest
import org.kotlin.mpp.mobile.data.entity.Message
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.domain.usecases.GetChat
import org.kotlin.mpp.mobile.presentation.BasePresenter
import org.kotlin.mpp.mobile.util.log
import kotlin.coroutines.CoroutineContext

class ChatPresenter(
    private val getChat: GetChat,
    private val sendMessage: SendMessage,
    private val markMessageAsRead: MarkMessageAsRead,
    private val coroutineContext: CoroutineContext = defaultDispatcher
) : BasePresenter<ChatView>(coroutineContext) {

    private lateinit var token: String

    private val chatId by lazy { view!!.chatId() }

    override fun onViewAttached(view: ChatView) {
        super.onViewAttached(view)
        token = view.token()
        loadChat()
    }

    fun loadChat() {
        scope.launch {
            getChat(
                params = ChatFullRequest(token, chatId),
                onSuccess = { view?.showChat(it) },
                onFailure = { view?.showChatLoadError(it) }
            )
        }
    }

    fun sendMessage(messageText: String) {
        view?.showMessage(Message(-1, chatId, 1167, messageText))
        scope.launch {
            log("Chat", "PRESENTER: Making sendMessage() request")
            sendMessage(
                params = SendMessageRequest(token, chatId, messageText),
                // TODO remove logs
                onSuccess = { log("Chat", "onSuccess ${it.messageId}") },
                onFailure = { log("Chat", "PRESENTER: onFailure() ${it.message}") }
            )
        }
    }

    fun markMessageAsRead(messageId: Int) {
        scope.launch {
            markMessageAsRead(
                params = MarkMessageRequest(token, chatId, messageId),
                onSuccess = {},
                onFailure = {}
            )
        }
    }
}