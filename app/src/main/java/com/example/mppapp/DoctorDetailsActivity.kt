package com.example.mppapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import data.DoctorApi
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DoctorDetailsActivity : AppCompatActivity(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details)

        val doctorApi = DoctorApi(OkHttp.create())

        launch(Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.IO) {
                    doctorApi.getDoctors()
                }
                Log.d("TAG", response.toString())
            } catch (e: Exception) {
                Log.d("TAG", "${e.message}")
            }
        }
    }
}
