package com.example.mppapp.ui.profile.password_change

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.mppapp.R
import com.example.mppapp.ui.profile.ProfileOptionsActivity
import com.example.mppapp.util.getAccessToken
import com.example.mppapp.util.getUserId
import kotlinx.android.synthetic.main.activity_login.*
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.data.entity.PasswordEditResponse
import org.kotlin.mpp.mobile.presentation.profile.password.PasswordChangeView
import kotlinx.android.synthetic.main.activity_password_change.*
import org.kotlin.mpp.mobile.util.log
import com.example.mppapp.util.CloseableActivity


class PasswordChangeActivity : CloseableActivity(R.layout.activity_password_change), PasswordChangeView {

    private val presenter = ServiceLocator.passwordChangePresenter


    override fun getToken(): String {
        return getAccessToken()
    }

    override fun getTheUserId(): Int {
        return getUserId()
    }

    override fun showChangeResponse(response: PasswordEditResponse) {
        when {
            response.data!=null -> {
                Toast.makeText(this, R.string.password_changed, Toast.LENGTH_SHORT).show()
            }
            response.errors!=null -> {
                val errors = response.errors!!
                if (errors.current_password!=null && errors.current_password!!.isNotEmpty())
                    Toast.makeText(this, errors.current_password!![0], Toast.LENGTH_SHORT).show()
                if (errors.password!=null && errors.password!!.isNotEmpty())
                    Toast.makeText(this, errors.password!![0], Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, R.string.password_fail, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun showError(message: String) {
        log("PasswordChange", message)
        Toast.makeText(this, R.string.password_fail, Toast.LENGTH_SHORT).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)

        buttonChange.setOnClickListener {
            val password = editPasswordOld.text.toString()
            val currentPassword = editPasswordNew.text.toString()
            val confirmPassword = editPasswordRepeat.text.toString()
            presenter.changePassword(password, currentPassword, confirmPassword)
        }
    }


    companion object {
        fun open(context: Context) {
            val intent = Intent(context, PasswordChangeActivity::class.java)
            context.startActivity(intent)
        }
    }
}
