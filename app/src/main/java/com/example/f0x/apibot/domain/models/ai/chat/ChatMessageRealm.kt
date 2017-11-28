package com.example.f0x.apibot.domain.models.ai.chat

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by f0x on 28.11.17.
 */
@RealmClass
open class ChatMessageRealm : RealmModel {
    companion object {
        const val TYPE_USER = 1
        const val TYPE_BOT = 2
    }

    @PrimaryKey
    var date: Long = 0L
    var message: String? = null
    var type: Int = TYPE_USER
}