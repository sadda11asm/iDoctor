package org.kotlin.mpp.mobile.presentation.profile.password

import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.data.entity.PasswordEditRequest
import org.kotlin.mpp.mobile.data.entity.PasswordEditResponse
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.domain.usecases.EditPassword
import org.kotlin.mpp.mobile.presentation.BasePresenter
import kotlin.coroutines.CoroutineContext

class PasswordChangePresenter(
    private val editPassword: EditPassword,
    private val coroutineContext: CoroutineContext = defaultDispatcher
) : BasePresenter<PasswordChangeView>(coroutineContext) {

    fun changePassword(password: String, currentPassword: String, confirmPassword: String) {
        val request = constructRequest(password, currentPassword, confirmPassword)
        scope.launch {
            editPassword(
                params = request,
                onSuccess = { view?.showChangeResponse(it)},
                onFailure = { view?.showError(it.message!!)}
            )
        }
    }

    private fun constructRequest(password: String, currentPassword: String, confirmPassword: String) : PasswordEditRequest {
        return PasswordEditRequest(view!!.getToken(), view!!.getTheUserId(), password, currentPassword, confirmPassword)
    }

}