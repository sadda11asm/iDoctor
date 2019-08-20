package com.example.mppapp.ui.profile.password_change

import android.content.Context
import android.content.Intent
import com.example.mppapp.R
import com.example.mppapp.util.CloseableActivity

class PasswordChangeActivity : CloseableActivity(R.layout.activity_password_change) {

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, PasswordChangeActivity::class.java)
            context.startActivity(intent)
        }
    }
}
