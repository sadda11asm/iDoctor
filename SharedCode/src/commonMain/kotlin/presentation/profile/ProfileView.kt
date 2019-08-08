package org.kotlin.mpp.mobile.presentation.profile

import data.entity.UserFull

interface ProfileView {

    fun token(): String

    fun showUserInfo(userFull: UserFull)

    fun openEditDataPage()

    fun logout()
}