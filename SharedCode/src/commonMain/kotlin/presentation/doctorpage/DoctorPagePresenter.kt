package presentation.doctorpage

import data.entity.CreateChatParams
import domain.usecases.CreateChat
import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.presentation.BasePresenter
import kotlin.coroutines.CoroutineContext

class DoctorPagePresenter (
    private val createChat: CreateChat,
    private val coroutineContext: CoroutineContext = defaultDispatcher
): BasePresenter<DoctorPageView>(coroutineContext) {

    fun createChat(token: String, title: String, userId: Int, anonymous: Boolean, doctorId: Int?, avatar: String) {
        view?.showLoader()
        scope.launch {
            createChat(
                params = CreateChatParams(token, title, userId, anonymous, doctorId),
                onSuccess = { view?.goToChat(it, avatar, title); view?.hideLoader() },
                onFailure = { view?.showCreationError(it); view?.hideLoader() }
            )
        }
    }
}