package org.kotlin.mpp.mobile.presentation.login

import data.entity.UserFull
import domain.usecases.GetUserInfo
import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.data.entity.AuthorizationResponse
import org.kotlin.mpp.mobile.data.entity.User
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.domain.usecases.AuthorizeUser
import org.kotlin.mpp.mobile.presentation.BasePresenter
import kotlin.coroutines.CoroutineContext

class LoginPresenter(
    private val authorizeUser: AuthorizeUser,
    private val getUserInfo: GetUserInfo,
    coroutineContext: CoroutineContext = defaultDispatcher
): BasePresenter<LoginView>(coroutineContext) {

    override fun onViewAttached(view: LoginView) {
        super.onViewAttached(view)
        view.showLoadingVisible(false)
    }

    fun onLogin(username: String, password: String) {
        scope.launch {
            authorizeUser(
                User(
                    username,
                    password
                ),
                onSuccess = { getUserInfo(it) },
//                onSuccess = { view?.showSuccessfulLogin(it, null) },
                onFailure = { view?.showFailedLogin(it) }
            )
        }
    }

    fun getUserInfo(response: AuthorizationResponse) {
        scope.launch {
            getUserInfo(
                response.access_token,
                onSuccess = { view?.showSuccessfulLogin(response, it)},
                onFailure = {view?.showFailedLogin(it)}
            )
        }
    }
}

interface LoginView {
    fun showLoadingVisible(visible: Boolean)
    fun showFailedLogin(e: Exception)
    fun showSuccessfulLogin(response: AuthorizationResponse, user: UserFull)

}
