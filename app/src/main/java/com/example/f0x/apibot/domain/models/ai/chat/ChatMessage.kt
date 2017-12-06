package com.example.f0x.apibot.domain.models.ai.chat

import com.example.f0x.apibot.presentation.main.IChatItem
import com.example.f0x.apibot.presentation.main.IChatItem.Companion.TYPE_USER

/**
 * Created by f0x on 29.11.17.
 */
data class ChatMessage(val date: Long = 0L, val message: String = "", val type: Int = TYPE_USER) : IChatItem {
    override fun getTypeItem(): Int = type

    override fun getText(): String = message

}