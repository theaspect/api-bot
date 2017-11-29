package com.example.f0x.apibot.domain.repository.settings.local

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by f0x on 29.11.17.
 */

@RealmClass
open class Settings : RealmModel {
    @PrimaryKey
    var key = 1L
    var isSoundEnabled: Boolean = false
}