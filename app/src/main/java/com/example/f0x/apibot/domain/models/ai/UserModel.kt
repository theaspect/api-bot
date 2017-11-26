package com.example.f0x.apibot.domain.models.ai

import com.example.f0x.apibot.presentation.common.presenters.IModel

/**
 * Created by f0x on 26.11.17.
 */
data class UserModel(val query: String = "unresolved"):IModel {
    override val type: Int
        get() = 2
}