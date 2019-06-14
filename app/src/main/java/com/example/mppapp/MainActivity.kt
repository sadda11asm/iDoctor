package com.example.mppapp

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mppapp.R
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.engine.okhttp.OkHttpEngine
import kotlinx.android.synthetic.main.activity_main.view.*
import org.kotlin.mpp.mobile.createApplicationScreenMessage
import org.kotlin.mpp.mobile.data.api.SpecializationApi

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var textView: TextView = this.findViewById(R.id.authorization_text_registration)

        //textView.text = createApplicationScreenMessage()

    }
}