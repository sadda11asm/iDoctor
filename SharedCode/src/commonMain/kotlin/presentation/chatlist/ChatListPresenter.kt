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
        view.showLoading()
    }

    fun onLoadChats(token: String) {
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
    fun showChats (chats: List<Chat>)
    fun showLoadFailed(e: Exception)
}