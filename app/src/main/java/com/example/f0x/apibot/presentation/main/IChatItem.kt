package com.example.f0x.apibot.presentation.main

/**
 * Created by f0x on 06.12.17.
 */
interface IChatItem {
    companion object {
        const val TYPE_USER = 1
        const val TYPE_BOT = 2
        const val TYPE_DATE = 3
    }

    fun getText(): String
    fun getTypeItem(): Int

}