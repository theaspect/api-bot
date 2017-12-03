package com.example.f0x.apibot.presentation.main

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.f0x.apibot.domain.models.ai.chat.ChatMessage
import com.example.f0x.apibot.presentation.common.IView

/**
 * Created by f0x on 14.11.17.
 */
interface IChatView : IView {
    fun addMessage(chatMessage: ChatMessage)
    fun unMuteMenuItem()
    fun muteMenuItem()
    fun clearChat()
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showAskView(b: Boolean)
}