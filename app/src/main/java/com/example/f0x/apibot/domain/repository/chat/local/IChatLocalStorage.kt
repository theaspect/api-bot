package com.example.f0x.apibot.domain.repository.chat.local

import com.example.f0x.apibot.domain.models.ai.chat.ChatMessageRealm

/**
 * Created by f0x on 28.11.17.
 */
interface IChatLocalStorage {
    fun allMessages(): List<ChatMessageRealm>
    fun saveMessage(chatMessageRealm: ChatMessageRealm)
    fun clearChatMessages()
}