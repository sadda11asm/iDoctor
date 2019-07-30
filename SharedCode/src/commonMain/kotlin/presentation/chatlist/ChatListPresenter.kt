package presentation.chatlist

import data.entity.Chat
import io.ktor.http.HttpProtocolVersion
import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.SocketListener
import org.kotlin.mpp.mobile.data.entity.ChatListRequest
import org.kotlin.mpp.mobile.data.entity.Message
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.domain.usecases.*
import org.kotlin.mpp.mobile.presentation.BasePresenter
import org.kotlin.mpp.mobile.util.log
import kotlin.coroutines.CoroutineContext

class ChatListPresenter(
    private val getChatList: GetChatList,
    private val receiveMessage: ReceiveMessage,
    private val saveChat: SaveChat,
    private val coroutineContext: CoroutineContext = defaultDispatcher
) : BasePresenter<ChatListView>(coroutineContext), SocketListener {

    override fun onMessage(mes: Message) {
        log("Sockets", "onMessage")
        scope.launch {
            receiveMessage(
                mes,
                onSuccess = { log("Sockets-Saved", "DODO"); onLoadChats(view?.getToken()!!, true) },
                onFailure = { log("Sockets", it.message!!) }
            )
        }
    }

    override fun onChatCreated(chat: Chat) {
        view?.showChat(chat)
        scope.launch {
            saveChat(
                chat,
                onSuccess = {},
                onFailure = { log("Sockets", it.message!!) }
            )
        }
    }

    override fun onViewAttached(view: ChatListView) {
        super.onViewAttached(view)
        view.showLoading(true)
    }

    fun onLoadCachedChats(token: String, connection: Boolean) {
        scope.launch {
            getChatList(
                params = ChatListRequest(token, connection, true),
                onSuccess = {
                    view?.showLoading(false)
                    view?.showChats(it.toMutableList())
                    onLoadChats(token, connection)
                },
                onFailure = {
                    view?.showLoading(false)
                    view?.showLoadFailed(it)
                }
            )
        }
    }

    fun onLoadChats(token: String, connection: Boolean) {
        scope.launch {
            getChatList(
                params = ChatListRequest(token, connection, false),
                onSuccess = {
                    view?.showLoading(false)
                    view?.showChats(it.toMutableList())
//                    subscribeToSocket()
                    log("ChatList", it.toString())
                },
                onFailure = {
                    view?.showLoading(false)
                    view?.showLoadFailed(it)
                    log("ChatList", it.message!!)
                }
            )
        }
    }

}

interface ChatListView {
    fun showLoading(loading: Boolean) // TODO add boolean loading param
    fun showChats(chats: MutableList<Chat>)
    fun showLoadFailed(e: Exception)
    fun showChat(chat: Chat)
    fun getToken(): String
}