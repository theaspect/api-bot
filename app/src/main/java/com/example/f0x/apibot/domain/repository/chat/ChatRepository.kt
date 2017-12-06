package com.example.f0x.apibot.domain.repository.chat

import com.example.f0x.apibot.domain.models.ai.chat.ChatMessage
import com.example.f0x.apibot.domain.repository.BaseRepository
import com.example.f0x.apibot.domain.repository.chat.local.IChatLocalStorage
import io.reactivex.subjects.ReplaySubject

class ChatRepository(val localStorage: IChatLocalStorage, val chatSubject: ReplaySubject<ChatMessage>) : BaseRepository(), IChatRepository {
    override fun saveMessage(message: String, type: Int) {
        val chatMessage = ChatMessage(System.currentTimeMillis(), message, type)
        localStorage.saveMessage(chatMessage)
        chatSubject.onNext(chatMessage)
    }


    override fun allMessages() {
        localStorage.allMessages().forEach { chatSubject.onNext(it) }
    }


    override fun clearChatMessages() {
        localStorage.clearChatMessages()
    }
}