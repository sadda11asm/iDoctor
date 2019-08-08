package org.kotlin.mpp.mobile.presentation.profile

import domain.usecases.GetUserInfo
import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.presentation.BasePresenter
import kotlin.coroutines.CoroutineContext

class ProfilePresenter(
    private val getUserInfo: GetUserInfo,
    private val coroutineContext: CoroutineContext = defaultDispatcher
) : BasePresenter<ProfileView>(coroutineContext) {

    private val token by lazy { view!!.token() }

    override fun onViewAttached(view: ProfileView) {
        loadUserInfo()
    }

    fun onLogout() {
        view?.logout()
    }

    private fun loadUserInfo() {
        scope.launch {
            getUserInfo(
                params = token,
                onSuccess = { view?.showUserInfo(it) },
                onFailure = { }
            )
        }
    }
}