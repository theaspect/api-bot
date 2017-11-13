package com.example.f0x.apibot.app.injections.global

import ai.api.AIConfiguration
import ai.api.android.AIService
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by f0x on 13.11.17.
 */

@Module
class AIModule {

    @Singleton
    @Provides
    fun provideAIService(context: Context): AIService {

        val config = ai.api.android.AIConfiguration("CLIENT_ACCESS_TOKEN",
                AIConfiguration.SupportedLanguages.English,
                ai.api.android.AIConfiguration.RecognitionEngine.System)

        return AIService.getService(context, config)
    }
}