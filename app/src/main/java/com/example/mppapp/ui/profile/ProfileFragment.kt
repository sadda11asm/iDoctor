package com.example.mppapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mppapp.R
import com.example.mppapp.ui.login.LoginActivity
import com.example.mppapp.util.deleteAccessToken
import com.example.mppapp.util.getAccessToken
import com.example.mppapp.util.getName
import data.entity.UserFull
import kotlinx.android.synthetic.main.fragment_profile.*
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.presentation.profile.ProfileView

class ProfileFragment : Fragment(), ProfileView {

    private val presenter by lazy { ServiceLocator.profilePresenter }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        buttonLogout.setOnClickListener { presenter.onLogout() }
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun token() = getAccessToken()

    override fun showUserInfo(userFull: UserFull) {
        textName.text = getName()
        textLocation.text = userFull.createdAt?.timezone
        textEmail.text = userFull.email
        textPhone.text = userFull.phone
    }

    override fun openEditDataPage() {
        TODO("not implemented")
    }

    override fun logout() {
        deleteAccessToken()
        LoginActivity.open(context!!)
        activity?.finish()
    }
}