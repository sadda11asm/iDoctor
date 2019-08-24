package org.kotlin.mpp.mobile.presentation.profile.password

import org.kotlin.mpp.mobile.data.entity.PasswordEditResponse

interface PasswordChangeView {

    fun getToken(): String

    fun getTheUserId(): Int

    fun showChangeResponse(passwordEditResponse: PasswordEditResponse)

    fun showError(message: String)
}