package com.example.mppapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import data.SpecializationsRepository
import framework.SpecializationSourceImpl
import kotlinx.coroutines.*
import org.kotlin.mpp.mobile.data.api.SpecializationApi
import org.kotlin.mpp.mobile.domain.ApiResult
import org.kotlin.mpp.mobile.usecases.GetDoctors

class MainActivity: AppCompatActivity() {

    val getDoctors: GetDoctors

    init {
        val specialization = SpecializationSourceImpl(SpecializationApi())
        val specRepo = SpecializationsRepository(specialization)
        getDoctors = GetDoctors(specRepo)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var textView: TextView = this.findViewById(R.id.authorization_text_registration)

        getSpecialization{
            Log.v("specializations", it.toString())
        }



//        Log.v("specialization", )

//        textView.text = createApplicationScreenMessage()

    }

    private fun getSpecialization(callback: (ApiResult) -> Unit) {
        MainScope().launch {
            async(Dispatchers.IO) {
                SpecializationApi().fetchSpecializations(callback)
            }.await()
        }
    }
}