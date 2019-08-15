package com.example.mppapp.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.crashlytics.android.Crashlytics
import com.example.mppapp.R
import com.example.mppapp.ui.MainActivity
import com.example.mppapp.util.getNetworkConnection
import com.example.mppapp.util.isTokenPresent
import com.example.mppapp.util.putToken
import com.example.mppapp.util.putUser
import data.entity.UserFull
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.activity_login.*
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.data.entity.AuthorizationResponse
import org.kotlin.mpp.mobile.presentation.login.LoginView

class LoginActivity: AppCompatActivity(), LoginView {

    private val presenter by lazy { ServiceLocator.loginPresenter }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Fabric.with(this, Crashlytics())

        if (isTokenPresent()) {
            MainActivity.open(this)
            finish()
            return
        }
        setListeners()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()

    }

    private fun setListeners() {
        buttonEnter.setOnClickListener { presenter.login(
            editEmail.text.toString(),
            editPassword.text.toString()
        ) }
    }

    override fun isConnectedToNetwork() = getNetworkConnection(this)

    override fun showErrorNoConnection() {
        Toast.makeText(this, R.string.login_no_connection, Toast.LENGTH_SHORT).show()
    }

    override fun showErrorEmptyFields() {
        Toast.makeText(this, R.string.login_empty_fields, Toast.LENGTH_SHORT).show()
    }

    override fun showErrorInvalidData() {
        Toast.makeText(this, R.string.login_invalid_data, Toast.LENGTH_SHORT).show()
    }

    override fun showLoader() {
        progressLogin.visibility = View.VISIBLE
        buttonEnter.alpha = 0.4F
        buttonEnter.text = null
        buttonEnter.isClickable = false
    }

    override fun hideLoader() {
        progressLogin.visibility = View.GONE
        buttonEnter.alpha = 1.0F
        buttonEnter.text = resources.getString(R.string.login_signin)
        buttonEnter.isClickable = true
    }

    override fun cacheAuthInfo(response: AuthorizationResponse, user: UserFull) {
        putToken(response)
        putUser(user)
    }

    override fun openMainPage() {
        MainActivity.open(this)
        finish()
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }
}