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
import org.kotlin.mpp.mobile.domain.usecases.ReceiveMessage
import org.kotlin.mpp.mobile.presentation.BasePresenter
import util.getTimeZoneOffset
import org.kotlin.mpp.mobile.util.log
import util.currentTime
import kotlin.coroutines.CoroutineContext
import kotlin.math.log

class ChatPresenter(
    private val getChatFull: GetChatFull,
    private val sendMessage: SendMessage,
    private val receiveMessage: ReceiveMessage,
    private val markMessageAsRead: MarkMessageAsRead,
    private val coroutineContext: CoroutineContext = defaultDispatcher
) : BasePresenter<ChatView>(coroutineContext), SocketListener {

    private lateinit var token: String

    private val chatId by lazy { view!!.chatId() }

    private val userId by lazy { view!!.userId() }

    override fun onViewAttached(view: ChatView) {
        super.onViewAttached(view)
        token = view.token()
        loadCachedChat(view.isConnectedToNetwork())
    }

    override fun onMessage(mes: Message) {
        if (mes.chatId == chatId) {
            if (mes.userId != userId) view?.showMessage(mes)
            scope.launch {
                receiveMessage(
                    mes,
                    onSuccess = {},
                    onFailure = { log("Sockets", it.message!!) }
                )
            }
        }
    }

    private fun loadCachedChat(connection: Boolean) {
        scope.launch {
            getChatFull(
                params = ChatFullRequest(token, chatId, connection, true),
                onSuccess = { view?.showChat(it); log("Chat", "onSuccess"); loadChat(connection) },
                onFailure = {
                    log("Chat", it.message!!)
                    view?.showChatLoadError(it)
                }
            )
        }
    }

    private fun loadChat(connection: Boolean) {
        scope.launch {
            getChatFull(
                params = ChatFullRequest(token, chatId, connection, false),
                onSuccess = { view?.showChat(it); log("Chat", "onSuccess") },
                onFailure = {
                    log("Chat", it.message!!)
                    view?.showChatLoadError(it)
                }
            )
        }
    }

    fun sendMessage(messageText: String) {
        view?.showMessage(Message(-1, userId, messageText))
        scope.launch {
            sendMessage(
                params = SendMessageRequest(token, chatId, messageText, userId),
                onSuccess = { log("SEND", currentTime) },
                onFailure = { log("SEND", it.message!!) }
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