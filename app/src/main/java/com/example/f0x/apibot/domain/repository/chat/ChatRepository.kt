package com.example.f0x.apibot.domain.repository.chat

import com.example.f0x.apibot.domain.models.ai.chat.ChatMessageRealm
import com.example.f0x.apibot.domain.repository.BaseRepository
import io.reactivex.Single

class ChatRepository() : BaseRepository(), IChatRepository {
    override fun allMessages(): Single<List<ChatMessageRealm>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveMessage(chatMessageRealm: ChatMessageRealm) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearChatMessages() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}