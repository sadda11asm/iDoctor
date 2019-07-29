package org.kotlin.mpp.mobile.presentation.login

import data.entity.UserFull
import domain.usecases.GetUserInfo
import io.ktor.client.features.ClientRequestException
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
    private val coroutineContext: CoroutineContext = defaultDispatcher
) : BasePresenter<LoginView>(coroutineContext) {

    fun login(username: String, password: String) {
        if(!isValid(username, password)) {
            view?.showErrorEmptyFields()
        } else if(!view!!.isConnectedToNetwork()) {
            view?.showErrorNoConnection()
        } else {
            makeLoginRequest(username, password)
        }
    }

    private fun makeLoginRequest(username: String, password: String) {
        view?.showLoader()
        scope.launch {
            authorizeUser(
                params = User(username, password),
                onSuccess = { getUserInfo(it) },
                onFailure = { onLoginFailure() }
            )
        }
    }

    private fun getUserInfo(response: AuthorizationResponse) {
        scope.launch {
            getUserInfo(
                params = response.accessToken,
                onSuccess = { onGetUserInfoSuccess(response, it) },
                onFailure = { onLoginFailure() }
            )
        }
    }

    private fun onLoginFailure() {
        // TODO add logic to distinguish between invalid data & server error
        view?.hideLoader()
        view?.showErrorInvalidData()
    }

    private fun onGetUserInfoSuccess(response: AuthorizationResponse, user: UserFull) {
        view?.cacheAuthInfo(response, user)
        view?.openMainPage()
    }

    private fun isValid(username: String, password: String) = username.isNotEmpty() && password.isNotEmpty()
}
