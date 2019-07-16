package com.alc1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        about_btn.setOnClickListener { startActivity(Intent(this, AboutActivity::class.java)) }
        profile_btn.setOnClickListener { startActivity(Intent(this, ProfileActivity::class.java)) }
    }
}
