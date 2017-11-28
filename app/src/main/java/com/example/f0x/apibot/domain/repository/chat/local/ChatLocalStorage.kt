package com.example.f0x.apibot.domain.repository.chat.local

import com.example.f0x.apibot.domain.models.ai.chat.ChatMessageRealm
import io.realm.Realm

class ChatLocalStorage : IChatLocalStorage {

    override fun allMessages(): List<ChatMessageRealm> {
        Realm.getDefaultInstance().use { realm ->
            return realm.copyFromRealm(realm.where(ChatMessageRealm::class.java).findAll())
        }
    }

    override fun saveMessage(chatMessageRealm: ChatMessageRealm) {

    }

    override fun clearChatMessages() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}