package com.example.f0x.apibot.domain.repository.chat

import com.example.f0x.apibot.domain.models.ai.chat.ChatMessageRealm
import io.reactivex.Single

/**
 * Created by f0x on 28.11.17.
 */
interface IChatRepository {

    fun allMessages(): Single<List<ChatMessageRealm>>
    fun saveMessage(chatMessageRealm: ChatMessageRealm)
    fun clearChatMessages()
}