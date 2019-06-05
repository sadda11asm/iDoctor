package com.example.mppapp

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mppapp.R
import kotlinx.android.synthetic.main.activity_main.view.*
import org.kotlin.mpp.mobile.createApplicationScreenMessage

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textView: TextView = findViewById(R.id.main_text)
        textView.text = createApplicationScreenMessage()

    }
}