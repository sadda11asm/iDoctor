package com.example.mppapp.ui.profile.contact_us

import android.content.Context
import android.content.Intent
import com.example.mppapp.R
import com.example.mppapp.util.CloseableActivity

class ContactUsActivity : CloseableActivity(R.layout.activity_contact_us) {

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, ContactUsActivity::class.java)
            context.startActivity(intent)
        }
    }
}
