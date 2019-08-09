package com.example.mppapp.ui.profile_edit

import android.content.Context
import android.content.Intent
import com.example.mppapp.R
import com.example.mppapp.util.ProfileOptionsActivity

class ProfileEditActivity : ProfileOptionsActivity(R.layout.activity_profile_edit) {

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, ProfileEditActivity::class.java)
            context.startActivity(intent)
        }
    }
}
