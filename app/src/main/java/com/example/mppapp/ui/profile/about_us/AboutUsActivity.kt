package com.example.mppapp.ui.profile.about_us

import android.content.Context
import android.content.Intent
import com.example.mppapp.R
import com.example.mppapp.ui.profile.ProfileOptionsActivity

class AboutUsActivity : ProfileOptionsActivity(R.layout.activity_about_us) {

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, AboutUsActivity::class.java)
            context.startActivity(intent)
        }
    }
}
