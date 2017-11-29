package com.example.f0x.apibot.app.injections.global

import ai.api.AIConfiguration
import ai.api.android.AIService
import android.content.Context
import android.speech.tts.TextToSpeech
import com.example.f0x.apibot.R
import com.example.f0x.apibot.domain.IPlayer
import com.example.f0x.apibot.domain.Player
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

/**
 * Created by f0x on 13.11.17.
 */

@Module
class AIModule {

    @Singleton
    @Provides
    fun provideAIService(context: Context): AIService {
        val res = context.resources
        val in_s = res.openRawResource(R.raw.key)
        val b = ByteArray(in_s.available())
        in_s.read(b)
        val key = String(b)

        val config =
                ai.api.android.AIConfiguration(key,
                        AIConfiguration.SupportedLanguages.English,
                        ai.api.android.AIConfiguration.RecognitionEngine.System)

        return AIService.getService(context, config)
    }

    @Provides
    @Singleton
    fun provideSpeechPlayer(context: Context): IPlayer {
        val tts = TextToSpeech(context, {})
        tts.language = Locale.ENGLISH
        return Player(tts)
    }
}