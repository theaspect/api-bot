package com.example.f0x.apibot.app.injections.global

import com.example.f0x.apibot.presentation.main.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by f0x on 13.11.17.
 */

@Component(modules = arrayOf(
        AppModule::class,
        AIModule::class)
)
@Singleton

interface AppComponent {
    fun inject(mainActivity: MainActivity)
}