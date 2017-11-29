package com.example.f0x.apibot.app.injections.global

import com.example.f0x.apibot.domain.models.ai.chat.ChatMessage
import com.example.f0x.apibot.domain.repository.chat.ChatRepository
import com.example.f0x.apibot.domain.repository.chat.IChatRepository
import com.example.f0x.apibot.domain.repository.chat.local.IChatLocalStorage
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.ReplaySubject
import javax.inject.Singleton

/**
 * Created by f0x on 29.11.17.
 */
@Module

class RepoModule {
    @Provides
    @Singleton
    fun provideChatSubject(): ReplaySubject<ChatMessage> = ReplaySubject.create()

    @Singleton
    @Provides
    fun provideChatRepo(localStorage: IChatLocalStorage, chatSubject: ReplaySubject<ChatMessage>): IChatRepository = ChatRepository(localStorage, chatSubject)

}