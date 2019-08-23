package com.example.mppapp.ui.profile.profile_edit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.mppapp.R
import com.example.mppapp.ui.profile.ProfileOptionsActivity
import com.example.mppapp.util.getAccessToken
import com.example.mppapp.util.getUserId
import data.entity.UserFull
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.presentation.profile.edit.EditInfoView
import kotlinx.android.synthetic.main.activity_profile_edit.*
import org.kotlin.mpp.mobile.util.log


class ProfileEditActivity : ProfileOptionsActivity(R.layout.activity_profile_edit), EditInfoView {

    private val TAG = "ProfileEdit"

    private lateinit var user: UserFull

    private val presenter by lazy { ServiceLocator.editInfoPresenter }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.attachView(this)

        buttonSave.setOnClickListener {
            val userFull = constructUser()
            presenter.updateUser(userFull, getAccessToken())
        }
    }

    private fun constructUser(): UserFull {
        user.firstName = editFirstName.text.toString()
        user.lastName = editLastName.text.toString()
        user.phone = editPhone.text.toString()
        user.email =  editEmail.text.toString()
        user.patronymic = editPatronymic.text.toString()
        return user
    }
    override fun getProfileUserId(): Int {
        return getUserId()
    }

    override fun showUserInfo(userFull: UserFull) {
        saveUser(userFull)
        editFirstName.setText(userFull.firstName)
        editLastName.setText(userFull.lastName)
        editPatronymic.setText(userFull.patronymic)
        editEmail.setText(userFull.email)
        editPhone.setText(userFull.phone)

    }

    override fun showFailedLoading(message: String) {
        log(TAG, message)
        Toast.makeText(this, R.string.upload_fail, Toast.LENGTH_SHORT).show()
    }

    override fun showSuccessfulUpdate(userFull: UserFull) {
        Toast.makeText(this, R.string.update_successful, Toast.LENGTH_SHORT).show()
        showUserInfo(userFull)
    }

    override fun showFailedUpdating(message: String) {
        log(TAG, message)
        Toast.makeText(this, R.string.update_fail, Toast.LENGTH_SHORT).show()
    }


    override fun saveUser(userFull: UserFull) {
        user = userFull
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, ProfileEditActivity::class.java)
            context.startActivity(intent)
        }
    }
}
