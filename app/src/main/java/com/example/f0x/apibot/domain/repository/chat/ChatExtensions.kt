package com.example.f0x.apibot.domain.repository.chat

import com.example.f0x.apibot.domain.models.ai.chat.ChatMessage
import com.example.f0x.apibot.domain.models.ai.chat.ChatMessageRealm
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by f0x on 29.11.17.
 */

fun ChatMessageRealm.map2Data(): ChatMessage = ChatMessage(this.date, this.message, this.type)

fun ChatMessage.mat2Realm(): ChatMessageRealm {
    val r = ChatMessageRealm()
    r.date = this.date
    r.message = this.message
    r.type = this.type
    return r
}

fun ChatMessage.getFormatedDate(): String = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault()).format(this.date)
