package com.example.mppapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mppapp.databinding.ActivityDoctorDetailsBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DoctorDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details)

        val binding: ActivityDoctorDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_doctor_details)



    }
}
