package com.example.f0x.apibot.app

import android.app.Application
import io.realm.Realm

/**
 * Created by f0x on 17.10.17.
 */
class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        initRealm()

    }

    private fun initRealm() {
        Realm.init(this)
    }
}