package presentation.chatlist

import data.entity.Chat
import kotlinx.coroutines.launch
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

    fun onLoadChats(token: String, connection: Boolean) {
        scope.launch {
            getChatList(
                params = Pair(token, connection),
                onSuccess = { view?.showChats(it)
                    view?.showLoading(false)
                },
                onFailure = { view?.showLoadFailed(it)
                    view?.showLoading(false)
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