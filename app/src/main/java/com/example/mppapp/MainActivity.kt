package com.example.mppapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import kotlinx.coroutines.*
import org.kotlin.mpp.mobile.data.entity.AuthorizationResponse
import org.kotlin.mpp.mobile.domain.ApiResult
import org.kotlin.mpp.mobile.usecases.GetDoctors

class MainActivity: AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textView: TextView = this.findViewById(R.id.main_text)


        val token = intent.extras.getString("token")



//        Log.v("specialization", )

        textView.text = token

    }


    companion object{
        fun open(context: Context, response: AuthorizationResponse) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("token", response.access_token)
            context.startActivity(intent)

        }
    }

}