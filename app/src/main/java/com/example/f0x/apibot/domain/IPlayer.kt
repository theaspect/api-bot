package com.example.f0x.apibot.domain

/**
 * Created by f0x on 29.11.17.
 */
interface IPlayer {
    fun play(message: String)
    fun stop()
    fun isPlaying(): Boolean
}