package com.example.f0x.apibot.app.injections.global

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


/**
 * Created by f0x on 13.11.17.
 */

@Module
class AppModule(private val appContext: Context, private val packageName: String = appContext.applicationContext.packageName) {
    companion object {
        const val NAMED_PACKAGE_NAME = "named_package_name"
    }

    @Singleton
    @Provides
    fun provideApplicationContext() = appContext

    @Singleton
    @Provides
    @Named(NAMED_PACKAGE_NAME)
    fun providePackageName() = packageName


}