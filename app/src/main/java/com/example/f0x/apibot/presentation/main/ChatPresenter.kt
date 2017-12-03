package com.example.f0x.apibot.presentation.main

import ai.api.AIListener
import ai.api.android.AIService
import ai.api.model.AIError
import ai.api.model.AIRequest
import ai.api.model.AIResponse
import android.os.Build
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.f0x.apibot.domain.IPlayer
import com.example.f0x.apibot.domain.models.ai.chat.ChatMessage
import com.example.f0x.apibot.domain.repository.chat.IChatRepository
import com.example.f0x.apibot.domain.repository.settings.ISettingsLocalStorage
import com.example.f0x.apibot.domain.repository.settings.local.Settings
import com.example.f0x.apibot.presentation.common.presenters.ABasePermissionPresenter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.ReplaySubject
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by f0x on 14.11.17.
 */

@InjectViewState
@Singleton
class ChatPresenter @Inject constructor(val service: AIService,
                                        val repository: IChatRepository,
                                        val subject: ReplaySubject<ChatMessage>,
                                        val player: IPlayer,
                                        val settingsLocalStorage: ISettingsLocalStorage
) : ABasePermissionPresenter<IChatView>(), AIListener {
    var isListening = false
    var isPaused = false
    var isAudioAccepted = false
    val settings: Settings = settingsLocalStorage.settings()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        invalidateMenu()
        repository.allMessages()
        subject.subscribe { viewState.addMessage(it) }

        isAudioAccepted = checkRecordAudioPermission()
        if (!isAudioAccepted && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            requestRecordAudioPermission()
    }

    init {
        service.setListener(this)
    }

    override fun onResult(result: AIResponse?) {
        viewState.showLoadingProgress(false)
        repository.saveMessage(result?.result?.resolvedQuery, ChatMessage.TYPE_USER)
        val speech = result?.result?.fulfillment?.speech
        if (speech != null) {
            repository.saveMessage(result.result.fulfillment.speech, ChatMessage.TYPE_BOT)
            if (settings.isSoundEnabled)
                player.play(speech)

        }
    }

    override fun onListeningStarted() {
        println("$tag onListeningStarted()")

    }

    override fun onAudioLevel(level: Float) {

    }

    override fun onError(error: AIError?) {
        viewState.showLoadingProgress(false)
        viewState.showToast(error?.message)
        println("$tag onError: " + error?.message)
    }

    override fun onListeningCanceled() {
        println("$tag onListeningCanceled()")
    }

    override fun onListeningFinished() {
        println("$tag onListeningFinished()")
    }

    override fun attachView(view: IChatView?) {
        if (isListening && isPaused) {
            service.resume()
            isPaused = false
        }
        super.attachView(view)
    }

    override fun detachView(view: IChatView) {
        if (isListening) {
            service.pause()
            isPaused = true
        }

        if (player.isPlaying())
            player.stop()

        super.detachView(view)
    }


    override fun onDestroy() {
        service.cancel()
        super.onDestroy()
    }


    fun micOn() {
        viewState.showAskView(true)
        if (player.isPlaying())
            player.stop()

        if (!isAudioAccepted) {
            viewState.showToast("Need mic permission!")
            return
        }
        if (!isListening) {
            service.startListening()
            isListening = true
        }
    }

    fun micOff() {
        viewState.showAskView(false)
        viewState.showLoadingProgress(true)
        if (isListening) {
            service.stopListening()
            isListening = false
        }
    }

    fun onSendClick(query: String) {
        Log.d(tag, "NEW QUERY $query")

        repository.saveMessage(query, ChatMessage.TYPE_USER)
        viewState.showLoadingProgress(true)
        val request = AIRequest()
        request.setQuery(query)
        Single.fromCallable<String> {
            val result = service.textRequest(request).result
            result.fulfillment.speech
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    repository.saveMessage(it, ChatMessage.TYPE_BOT)
                    viewState.showLoadingProgress(false)
                    if (it != null && settings.isSoundEnabled) {
                        player.play(it)
                    }
                }, {
                    viewState.showLoadingProgress(false)
                    viewState.showToast(it.message)
                })

    }

    fun onMuteClick() {
        invalidateMenu()
        if (settings.isSoundEnabled) {
            player.stop()
        }

        settings.isSoundEnabled = !settings.isSoundEnabled
        settingsLocalStorage.saveSettings(settings)

    }

    private fun invalidateMenu() {
        if (settings.isSoundEnabled)
            viewState.muteMenuItem()
        else
            viewState.unMuteMenuItem()
    }

    fun clearChat() {
        if (player.isPlaying())
            player.stop()
        repository.clearChatMessages()
        viewState.clearChat()

    }

    fun onMessageClick(chatMessage: ChatMessage) {
        if (chatMessage.message == null)
            return
        if (settings.isSoundEnabled) {
            player.play(chatMessage.message)
        }
    }
}