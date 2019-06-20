package org.kotlin.mpp.mobile.presentation.login

import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.data.entity.AuthorizationResponse
import org.kotlin.mpp.mobile.data.entity.User
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.domain.usecases.AuthorizeUser
import org.kotlin.mpp.mobile.presentation.BasePresenter
import kotlin.coroutines.CoroutineContext

class LoginPresenter(
    private val authorizeUser: AuthorizeUser,
    private val coroutineContext: CoroutineContext = defaultDispatcher
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
                onSuccess = { view?.showSuccessfulLogin(it) },
                onFailure = { view?.showFailedLogin() }
            )
        }
    }
}

interface LoginView {
    fun showLoadingVisible(visible: Boolean)
    fun showFailedLogin()
    fun showSuccessfulLogin(response: AuthorizationResponse)
}
