package com.example.f0x.apibot.domain.repository.chat.local

import com.example.f0x.apibot.domain.models.ai.chat.ChatMessage
import com.example.f0x.apibot.domain.models.ai.chat.ChatMessageRealm
import com.example.f0x.apibot.domain.repository.BaseLocalStorageRealm
import com.example.f0x.apibot.domain.repository.chat.map2Data
import com.example.f0x.apibot.domain.repository.chat.mat2Realm
import io.realm.Realm

class ChatLocalStorage : BaseLocalStorageRealm(), IChatLocalStorage {

    override fun allMessages(): List<ChatMessage> {
        Realm.getDefaultInstance().use { realm ->
            return realm.copyFromRealm(realm.where(ChatMessageRealm::class.java).findAll()).map { it.map2Data() }
        }
    }

    override fun saveMessage(chatMessage: ChatMessage) {
        Realm.getDefaultInstance().use { realm ->
            saveChanges(chatMessage.mat2Realm(), realm)
        }
    }

    override fun clearChatMessages() {
        Realm.getDefaultInstance().use { realm ->
            delete(ChatMessageRealm::class.java, realm)
        }
    }
}