package com.example.mppapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.viewpager.widget.ViewPager
import com.example.mppapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewPager.adapter = MainPagerAdapter(supportFragmentManager)
        viewPager.offscreenPageLimit = 3

        navigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_doctors -> viewPager.currentItem = 0
                R.id.navigation_chats -> viewPager.currentItem = 1
                R.id.navigation_profile -> viewPager.currentItem = 2
            }
            true
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                TODO("not implemented")
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                changeToolbarTitle(position)
            }

            override fun onPageSelected(position: Int) {
                changeToolbarTitle(position)
            }

            private fun changeToolbarTitle(position: Int) {
                when(position) {
                    0 -> toolbar.title = resources.getString(R.string.toolbar_doctors)
                    1 -> toolbar.title = resources.getString(R.string.toolbar_chats)
                    2 -> toolbar.title = resources.getString(R.string.toolbar_profile)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = resources.getString(R.string.search)
        return true
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}