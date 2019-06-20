package com.example.mppapp

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mppapp.databinding.ActivityLoginBinding
import com.orhanobut.hawk.Hawk
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.data.entity.AuthorizationResponse
import org.kotlin.mpp.mobile.presentation.login.LoginView

class LoginActivity: AppCompatActivity(), LoginView {

    private val logTag = LoginActivity::class.java.simpleName

    private val presenter by lazy { ServiceLocator.loginPresenter}
    private lateinit var binding:ActivityLoginBinding


    override fun showLoadingVisible(visible: Boolean) {
        if (visible) {
            binding.progressBar.visibility = View.VISIBLE
            binding.loginSection.visibility = View.INVISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
            binding.loginSection.visibility = View.VISIBLE
        }
    }

    override fun showFailedLogin() {
        Toast.makeText(this, "Error occurred", Toast.LENGTH_LONG).show()
    }

    override fun showSuccessfulLogin(response: AuthorizationResponse) {
        Hawk.put("access_token", response.access_token)
        Hawk.put("refresh_token", response.refresh_token)
        Hawk.put("expire_in", response.expires_in)
        Hawk.put("token_type", response.token_type)
        MainActivity.open(this)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            presenter.onLogin(binding.authorizationTextLogin.text.toString(), binding.authorizationTextPass.text.toString())
        }
    }
}