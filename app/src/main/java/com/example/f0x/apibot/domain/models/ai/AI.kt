package com.example.f0x.apibot.domain.models.ai

import ai.api.model.AIError
import ai.api.model.AIResponse
import com.example.f0x.apibot.presentation.common.presenters.IModel

/**
 * Created by f0x on 13.11.17.
 */
data class AIModel(val response: AIResponse? = null, val error: AIError? = null) : IModel {
    override val type: Int
        get() = 1
}