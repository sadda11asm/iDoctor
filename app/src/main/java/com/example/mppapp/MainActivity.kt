package com.example.mppapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import org.kotlin.mpp.mobile.domain.ApiResult
import org.kotlin.mpp.mobile.usecases.GetDoctors

class MainActivity: AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var textView: TextView = this.findViewById(R.id.authorization_text_registration)



//        Log.v("specialization", )

//        textView.text = createApplicationScreenMessage()

    }


}