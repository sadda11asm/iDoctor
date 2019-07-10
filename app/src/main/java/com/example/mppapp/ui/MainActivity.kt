package com.example.mppapp.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.mppapp.R
import com.example.mppapp.ui.chatlist.ChatListFragment
import com.example.mppapp.ui.doctors_list.DoctorListFragment
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, ChatListFragment())
            .commit()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val bottom = findViewById<BottomNavigationView>(R.id.navigationView)
        bottom.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_chats -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, ChatListFragment())
                        .commit()
                }
//                R.id.action_carwashes -> {
//                    navigationController.navigateToCarWashes()
//                    container.visibility = View.VISIBLE
//                    Toast.makeText(this, "carwashes", Toast.LENGTH_SHORT).show()
//                }
//                R.id.action_profile -> {
////                    navigationController.navigateToProfile()
//                }
            }
            true
        }
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}