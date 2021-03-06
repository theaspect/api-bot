package com.example.f0x.apibot.app

import android.app.Application
import com.example.f0x.apibot.presentation.main.ChatActivity
import io.realm.Realm

/**
 * Created by f0x on 17.10.17.
 */
class AppController : Application() {

    companion object {
        private lateinit var componentProvider: ComponentProvider
        fun injectInMain(chatActivity: ChatActivity) {
            componentProvider.injectInMainActivity(chatActivity)
        }
    }


    override fun onCreate() {
        super.onCreate()
        initRealm()
        componentProvider = ComponentProvider(this)

    }

    private fun initRealm() {
        Realm.init(this)
    }
}