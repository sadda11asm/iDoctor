package org.kotlin.mpp.mobile.presentation.chat

import data.entity.MarkMessageRequest
import data.entity.SendMessageRequest
import domain.usecases.MarkMessageAsRead
import domain.usecases.SendMessage
import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.data.entity.ChatFullRequest
import org.kotlin.mpp.mobile.data.entity.Message
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.domain.usecases.GetChatFull
import org.kotlin.mpp.mobile.presentation.BasePresenter
import org.kotlin.mpp.mobile.util.log
import kotlin.coroutines.CoroutineContext

class ChatPresenter(
    private val getChatFull: GetChatFull,
    private val sendMessage: SendMessage,
    private val markMessageAsRead: MarkMessageAsRead,
    private val coroutineContext: CoroutineContext = defaultDispatcher
) : BasePresenter<ChatView>(coroutineContext) {

    private lateinit var token: String

    private val chatId by lazy { view!!.chatId() }

    private val userId by lazy { view!!.userId() }

    override fun onViewAttached(view: ChatView) {
        super.onViewAttached(view)
        token = view.token()
        loadChat()
    }

    fun loadChat() {
        scope.launch {
            getChatFull(
                params = ChatFullRequest(token, chatId),
                onSuccess = { view?.showChat(it) },
                onFailure = { view?.showChatLoadError(it) }
            )
        }
    }

    fun sendMessage(messageText: String) {
        view?.showMessage(Message(-1, chatId, userId, messageText))
        scope.launch {
            sendMessage(
                params = SendMessageRequest(token, chatId, messageText),
                onSuccess = {},
                onFailure = {}
            )
        }
    }

    fun markMessageAsRead(messageId: Int) {
        log("Chat", "MESSAGE ID: $messageId")
        scope.launch {
            markMessageAsRead(
                params = MarkMessageRequest(token, chatId, messageId),
                onSuccess = {},
                onFailure = {}
            )
        }
    }
}