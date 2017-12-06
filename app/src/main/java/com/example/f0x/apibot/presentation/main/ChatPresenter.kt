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
import java.text.SimpleDateFormat
import java.util.*
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
    val dateFormat = SimpleDateFormat("MMMM d", Locale.getDefault())
    var lastMessageDate: Long = 0L

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        invalidateMenu()
        repository.allMessages()
        subject.subscribe {
            if (lastMessageDate == 0L) {
                viewState.addMessage(createDateItem(it.date))

            } else {
                if (isNextDay(lastMessageDate, it.date))
                    viewState.addMessage(createDateItem(it.date))
            }
            lastMessageDate = it.date
            viewState.addMessage(it)
        }

        isAudioAccepted = checkRecordAudioPermission()
        if (!isAudioAccepted && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            requestRecordAudioPermission()
    }

    init {
        service.setListener(this)
    }

    override fun onResult(result: AIResponse?) {
        viewState.showLoadingProgress(false)
        var text = ""
        result?.result?.resolvedQuery?.let { text = it }

        repository.saveMessage(text, IChatItem.TYPE_USER)
        val speech = result?.result?.fulfillment?.speech
        if (speech != null) {
            repository.saveMessage(speech, IChatItem.TYPE_BOT)
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
        super.detachView(view)
    }


    override fun onDestroy() {
        service.cancel()
        if (player.isPlaying())
            player.stop()
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

        repository.saveMessage(query, IChatItem.TYPE_USER)
        viewState.showLoadingProgress(true)
        val request = AIRequest()
        request.setQuery(query)
        Single.fromCallable<String> {
            val result = service.textRequest(request).result
            result.fulfillment.speech
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    repository.saveMessage(it, IChatItem.TYPE_BOT)
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

    fun onMessageClick(chatMessage: IChatItem) {
        if (settings.isSoundEnabled) {
            player.play(chatMessage.getText())
        }
    }

    private fun createDateItem(date: Long): IChatItem =
            ChatMessage(date, dateFormat.format(date), IChatItem.TYPE_DATE)

    private fun isNextDay(previous: Long, current: Long): Boolean {
        val prev = Calendar.getInstance()
        prev.time = Date(previous)
        val curr = Calendar.getInstance()
        curr.time = Date(current)
        return curr.get(Calendar.DAY_OF_YEAR) - prev.get(Calendar.DAY_OF_YEAR) >= 1
    }
}