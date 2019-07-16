package com.example.mppapp.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.crashlytics.android.Crashlytics
import com.example.mppapp.R
import com.example.mppapp.databinding.ActivityLoginBinding
import com.example.mppapp.ui.MainActivity
import com.example.mppapp.util.putAuthResponse
import com.example.mppapp.util.putUser
import com.orhanobut.hawk.Hawk
import data.entity.UserFull
import io.fabric.sdk.android.Fabric
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.data.entity.AuthorizationResponse
import org.kotlin.mpp.mobile.presentation.login.LoginView

class LoginActivity: AppCompatActivity(), LoginView {

    private val logTag = LoginActivity::class.java.simpleName

    private val presenter by lazy { ServiceLocator.loginPresenter }
    private lateinit var binding: ActivityLoginBinding


    override fun showLoadingVisible(visible: Boolean) {
        if (visible) {
            binding.progressBar.visibility = View.VISIBLE
            binding.loginSection.visibility = View.INVISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
            binding.loginSection.visibility = View.VISIBLE
        }
    }

    override fun showFailedLogin(e: Exception) {
        Log.v("LoginActivity", e.toString())
        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
    }

    override fun showSuccessfulLogin(response: AuthorizationResponse, user: UserFull) {
        putAuthResponse(response)
        putUser(user)
        MainActivity.open(this)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Fabric.with(this, Crashlytics())

        if (Hawk.contains("access_token")) {
            MainActivity.open(this)
            finish()
            return
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        initListeners(binding)


    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()

    }

    private fun initListeners(binding: ActivityLoginBinding) {
        binding.authorizationButtonEnter.setOnClickListener {
            showLoadingVisible(true)
            presenter.onLogin(
                binding.authorizationTextLogin.text.toString(),
                binding.authorizationTextPass.text.toString()
            )
        }
    }
}