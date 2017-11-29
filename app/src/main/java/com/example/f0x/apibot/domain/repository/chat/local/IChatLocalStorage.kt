package com.example.f0x.apibot.domain.repository.chat.local

import com.example.f0x.apibot.domain.models.ai.chat.ChatMessage

/**
 * Created by f0x on 28.11.17.
 */
interface IChatLocalStorage {
    fun allMessages(): List<ChatMessage>
    fun saveMessage(chatMessageRealm: ChatMessage)
    fun clearChatMessages()
}