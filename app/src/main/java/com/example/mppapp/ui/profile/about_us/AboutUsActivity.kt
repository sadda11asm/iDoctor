package com.example.mppapp.ui.profile.about_us

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mppapp.R
import com.example.mppapp.ui.profile.password_change.PasswordChangeActivity
import com.example.mppapp.util.ProfileOptionsActivity

class AboutUsActivity : ProfileOptionsActivity(R.layout.activity_about_us) {

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, AboutUsActivity::class.java)
            context.startActivity(intent)
        }
    }
}
