package com.example.mppapp.ui.profile.contact_us

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mppapp.R
import com.example.mppapp.ui.profile.ProfileOptionsActivity
import com.example.mppapp.ui.profile.about_us.AboutUsActivity

class ContactUsActivity : ProfileOptionsActivity(R.layout.activity_contact_us) {

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, ContactUsActivity::class.java)
            context.startActivity(intent)
        }
    }
}
