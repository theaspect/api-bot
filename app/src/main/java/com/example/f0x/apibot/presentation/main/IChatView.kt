package com.example.f0x.apibot.presentation.main

import com.example.f0x.apibot.domain.models.ai.chat.ChatMessage
import com.example.f0x.apibot.presentation.common.IView

/**
 * Created by f0x on 14.11.17.
 */
interface IChatView : IView {
    fun disableMic()
    fun addMessage(chatMessage: ChatMessage)
}