package com.example.f0x.apibot.domain.repository.settings.local

import com.example.f0x.apibot.domain.repository.BaseLocalStorageRealm
import com.example.f0x.apibot.domain.repository.settings.ISettingsLocalStorage
import io.realm.Realm

class SettingsLocalStorage : BaseLocalStorageRealm(), ISettingsLocalStorage {
    override fun saveSettings(settings: Settings) {
        Realm.getDefaultInstance().use { realm ->
            saveChanges(settings, realm)
        }
    }

    override fun settings(): Settings {
        Realm.getDefaultInstance().use { realm ->
            var settings = realm.where(Settings::class.java).findFirst()
            if (settings == null) {
                realm.beginTransaction()
                settings = Settings()
                realm.commitTransaction()
                return settings
            }
            return realm.copyFromRealm(settings)
        }
    }
}