package com.example.f0x.apibot.domain.repository.chat

/**
 * Created by f0x on 28.11.17.
 */
interface IChatRepository {

    fun allMessages()
    fun saveMessage(message: String?, type: Int)
    fun clearChatMessages()
}