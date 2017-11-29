package com.example.f0x.apibot.domain.repository.settings

import com.example.f0x.apibot.domain.repository.settings.local.Settings

/**
 * Created by f0x on 29.11.17.
 */
interface ISettingsLocalStorage {
    fun settings(): Settings
    fun saveSettings(settings: Settings)
}