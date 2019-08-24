package org.kotlin.mpp.mobile.presentation.profile.edit

import data.entity.UserFull
import io.ktor.client.features.json.defaultSerializer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.data.entity.UserEditRequest
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.domain.usecases.EditUserInfo
import org.kotlin.mpp.mobile.domain.usecases.FetchUserInfo
import org.kotlin.mpp.mobile.presentation.BasePresenter
import kotlin.coroutines.CoroutineContext

class EditInfoPresenter (
    private val fetchUserInfo: FetchUserInfo,
    private val editUserInfo: EditUserInfo,
    private val coroutineContext: CoroutineContext = defaultDispatcher
): BasePresenter<EditInfoView>(coroutineContext) {


    override fun onViewAttached(view: EditInfoView) {
        super.onViewAttached(view)
        fetchUser()
    }


    private fun fetchUser() {
        scope.launch {
            fetchUserInfo(
                params = view?.getProfileUserId()!!,
                onSuccess = { view?.showUserInfo(it) },
                onFailure = { view?.showFailedLoading(it.message!!)}
            )
        }
    }


    fun updateUser(userFull: UserFull, token: String) {
        scope.launch {
            editUserInfo(
                params = UserEditRequest(token, userFull),
                onSuccess = { view?.showSuccessfulUpdate(it) },
                onFailure = { view?.showFailedUpdating(it.message!!) }
            )
        }
    }

}