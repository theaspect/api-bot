package com.example.f0x.apibot.domain.models.ai

import ai.api.model.AIError
import ai.api.model.AIResponse

/**
 * Created by f0x on 13.11.17.
 */
data class AIModel(val response: AIResponse? = null, val error: AIError? = null) {
}