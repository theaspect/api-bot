package com.example.f0x.apibot.domain

import android.speech.tts.TextToSpeech

class Player(val tts: TextToSpeech) : IPlayer {

    override fun isPlaying(): Boolean = tts.isSpeaking

    override fun play(message: String) {
        tts.speak(message, TextToSpeech.QUEUE_FLUSH, null)
    }

    override fun stop() {
        tts.stop()
    }
}