package com.example.f0x.apibot.domain.models.ai.chat

import com.example.f0x.apibot.presentation.main.IChatItem.Companion.TYPE_USER
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by f0x on 28.11.17.
 */
@RealmClass
open class ChatMessageRealm : RealmModel {

    @PrimaryKey
    var date: Long = 0L
    var message: String? = null
    var type: Int = TYPE_USER
}