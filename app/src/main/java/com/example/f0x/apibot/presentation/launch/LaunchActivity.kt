package com.example.f0x.apibot.presentation.launch

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.f0x.apibot.R
import com.example.f0x.apibot.presentation.main.ChatActivity

/**
 * Created by f0x on 01.12.17.
 */


class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        val i = Intent(this, ChatActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
        finish()
    }
}