package com.example.mppapp.ui.password_change

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mppapp.R
import com.example.mppapp.ui.profile_edit.ProfileEditActivity
import com.example.mppapp.util.ProfileOptionsActivity

class PasswordChangeActivity : ProfileOptionsActivity(R.layout.activity_password_change) {

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, PasswordChangeActivity::class.java)
            context.startActivity(intent)
        }
    }
}
