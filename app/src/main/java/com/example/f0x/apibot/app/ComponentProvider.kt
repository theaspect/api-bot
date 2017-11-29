package com.example.f0x.apibot.app

import android.content.Context
import com.example.f0x.apibot.app.injections.global.AppComponent
import com.example.f0x.apibot.app.injections.global.AppModule
import com.example.f0x.apibot.app.injections.global.DaggerAppComponent
import com.example.f0x.apibot.presentation.main.ChatActivity

/**
 * Created by f0x on 14.11.17.
 */
class ComponentProvider(context: Context) {

    private val appComponent: AppComponent =
            DaggerAppComponent.builder()
                    .appModule(AppModule(context))

                    .build()

    fun injectInMainActivity(chatActivity: ChatActivity) {
        appComponent.inject(chatActivity)
    }

}