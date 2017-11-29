package com.example.f0x.apibot.domain.models.ai.chat

/**
 * Created by f0x on 29.11.17.
 */
data class ChatMessage(val date: Long = 0L, val message: String? = null, val type: Int = TYPE_USER) {
    companion object {
        const val TYPE_USER = 1
        const val TYPE_BOT = 2
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}