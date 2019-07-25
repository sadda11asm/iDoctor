package presentation.chatlist

import data.entity.Chat
import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.data.entity.ChatListRequest
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.domain.usecases.GetChatList
import org.kotlin.mpp.mobile.presentation.BasePresenter
import kotlin.coroutines.CoroutineContext

class ChatListPresenter(
    private val getChatList: GetChatList,
    private val coroutineContext: CoroutineContext = defaultDispatcher
) : BasePresenter<ChatListView>(coroutineContext) {

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
                    view?.showChats(it)
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
                    view?.showChats(it)
                },
                onFailure = {
                    view?.showLoading(false)
                    view?.showLoadFailed(it)
                }
            )
        }
    }
}

interface ChatListView {
    fun showLoading(loading: Boolean) // TODO add boolean loading param
    fun showChats (chats: List<Chat>)
    fun showLoadFailed(e: Exception)
}