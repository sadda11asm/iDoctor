package com.example.mppapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.hawk.Hawk
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.data.entity.DoctorResponse
import org.kotlin.mpp.mobile.presentation.doctorlist.DoctorListView

class MainActivity: AppCompatActivity(), DoctorListView {

    private val presenter by lazy { ServiceLocator.doctorListPresenter }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val token = Hawk.get<String>("access_token")
        presenter.attachView(this)
        presenter.onLoadDoctors()
    }

    override fun showDoctors(doctorResponse: DoctorResponse) {
        Log.d("TAG", doctorResponse.toString())
    }

    override fun showLoadFailed(e: Exception) {
        Log.d("TAG", "ERROR ${e.message}")
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

}