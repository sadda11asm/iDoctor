package org.kotlin.mpp.mobile.presentation.chat

import data.entity.MarkMessageRequest
import data.entity.SendMessageRequest
import domain.usecases.MarkMessageAsRead
import domain.usecases.SendMessage
import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.SocketListener
import org.kotlin.mpp.mobile.data.entity.ChatFullRequest
import org.kotlin.mpp.mobile.data.entity.Message
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.domain.usecases.GetChatFull
import org.kotlin.mpp.mobile.domain.usecases.Subscribe
import org.kotlin.mpp.mobile.domain.usecases.Unsubscribe
import org.kotlin.mpp.mobile.presentation.BasePresenter
import util.getTimeZoneOffset
import org.kotlin.mpp.mobile.util.log
import util.currentTime
import kotlin.coroutines.CoroutineContext
import kotlin.math.log

class ChatPresenter(
    private val getChatFull: GetChatFull,
    private val sendMessage: SendMessage,
    private val subscribe: Subscribe,
    private val unsubscribe: Unsubscribe,
    private val markMessageAsRead: MarkMessageAsRead,
    private val coroutineContext: CoroutineContext = defaultDispatcher
) : BasePresenter<ChatView>(coroutineContext), SocketListener {

    private lateinit var token: String

    private val chatId by lazy { view!!.chatId() }

    private val userId by lazy { view!!.userId() }

    override fun onViewAttached(view: ChatView) {
        super.onViewAttached(view)
        token = view.token()
        loadChat(view.getConnection())
    }

    override fun onViewDetached() {
        super.onViewDetached()
        unsubscribeFromSocket()
    }


    override fun onMessage(mes: String) {
        view?.showMessage(Message(1000, 1000, mes, "2019-07-24T15:30:20"))
    }

    private fun subscribeToSocket() {
        val listener = this
        scope.launch {
            subscribe(
                listener,
                onSuccess = {},
                onFailure = {log("Sockets", it.message!!)}
            )
        }
    }

    private fun unsubscribeFromSocket() {
        scope.launch {
            unsubscribe(
                UseCase.None,
                onSuccess = {},
                onFailure = {log("UNSUBSCRIBE", it.message!!)}
            )
        }
    }
    fun loadChat(connection: Boolean) {
        scope.launch {
            getChatFull(
                params = ChatFullRequest(token, chatId, connection),
                onSuccess = { view?.showChat(it); log("Chat", "onSuccess") ; subscribeToSocket() },
                onFailure = { view?.showChatLoadError(it) }
            )
        }
    }

    fun sendMessage(messageText: String) {
        view?.showMessage(Message(-1, userId, messageText))
        scope.launch {
            sendMessage(
                params = SendMessageRequest(token, chatId, messageText, userId),
                onSuccess = {log("SEND", currentTime)},
                onFailure = {log("SEND", it.message!!)}
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