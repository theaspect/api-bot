package com.example.f0x.apibot.app.injections.global

import com.example.f0x.apibot.domain.repository.chat.local.ChatLocalStorage
import com.example.f0x.apibot.domain.repository.chat.local.IChatLocalStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by f0x on 29.11.17.
 */
@Module
class LocalStorageModule {
    @Provides
    @Singleton
    fun provideChatLocalStorage(): IChatLocalStorage = ChatLocalStorage()
}