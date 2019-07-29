package org.kotlin.mpp.mobile.presentation.login

import data.entity.UserFull
import org.kotlin.mpp.mobile.data.entity.AuthorizationResponse

interface LoginView {

    fun isConnectedToNetwork(): Boolean

    fun showErrorNoConnection()

    fun showErrorEmptyFields()

    fun showErrorInvalidData()

    fun showLoader()

    fun hideLoader()

    fun cacheAuthInfo(response: AuthorizationResponse, user: UserFull)

    fun openMainPage()
}