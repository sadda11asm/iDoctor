package org.kotlin.mpp.mobile.presentation.profile.edit

import data.entity.UserFull

interface EditInfoView {


    fun getProfileUserId(): Int

    fun showUserInfo(userFull: UserFull)

    fun showFailedLoading(message: String)

    fun showSuccessfulUpdate(userFull: UserFull)

    fun showFailedUpdating(message: String)

    fun saveUser(userFull: UserFull)

}