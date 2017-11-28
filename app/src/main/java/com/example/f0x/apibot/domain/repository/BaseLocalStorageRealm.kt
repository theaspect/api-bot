package com.example.f0x.apibot.domain.repository

import io.realm.Realm
import io.realm.RealmModel

/**
 * Created by f0x on 28.11.17.
 */
open class BaseLocalStorageRealm {

    fun <T : RealmModel> saveChanges(realmObject: T, realm: Realm): T {
        realm.beginTransaction()
        val result = realm.copyFromRealm(realm.copyToRealmOrUpdate(realmObject))
        realm.commitTransaction()
        return result
    }


    fun <T : RealmModel> saveChanges(realmObjects: List<T>, realm: Realm): List<T> {
        realm.beginTransaction()
        val result = realm.copyFromRealm(realm.copyToRealmOrUpdate(realmObjects))
        realm.commitTransaction()
        return result
    }
}

