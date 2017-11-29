package com.example.f0x.apibot.presentation.main

import ai.api.AIListener
import ai.api.android.AIService
import ai.api.model.AIError
import ai.api.model.AIRequest
import ai.api.model.AIResponse
import android.os.Build
import com.arellomobile.mvp.InjectViewState
import com.example.f0x.apibot.domain.models.ai.chat.ChatMessage
import com.example.f0x.apibot.domain.repository.chat.IChatRepository
import com.example.f0x.apibot.presentation.common.presenters.ABasePermissionPresenter
import io.reactivex.subjects.ReplaySubject
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by f0x on 14.11.17.
 */

@InjectViewState
@Singleton
class ChatPresenter @Inject constructor(val service: AIService, val repository: IChatRepository, val subject: ReplaySubject<ChatMessage>) : ABasePermissionPresenter<IChatView>(), AIListener {
    var isListening = false
    var isPaused = false
    var isAudioAccepted = false


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repository.allMessages()
        isAudioAccepted = checkRecordAudioPermission()
        if (!isAudioAccepted && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            requestRecordAudioPermission()
    }

    init {
        service.setListener(this)
    }

    override fun onResult(result: AIResponse?) {
        println("$tag onResult: " + result?.result?.resolvedQuery)
        repository.saveMessage(result?.result?.resolvedQuery, ChatMessage.TYPE_USER)
        repository.saveMessage(result?.result?.fulfillment?.speech, ChatMessage.TYPE_BOT)

    }

    override fun onListeningStarted() {
        println("$tag onListeningStarted()")

    }

    override fun onAudioLevel(level: Float) {

    }

    override fun onError(error: AIError?) {
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
        addDisposable(subject.subscribe {
            viewState.addMessage(it)
        })
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
        super.onDestroy()
    }


    fun micOn() {
        if (!isAudioAccepted) {
            viewState.showToast("Need mic permission!")
            viewState.disableMic()
            return
        }
        if (!isListening) {
            service.startListening()
            isListening = true
        }

    }

    fun micOff() {
        if (isListening) {
            service.stopListening()
            isListening = false
        }

    }

    fun onSendClick(query: String) {
        val request = AIRequest()
        request.setQuery(query)
        Thread(Runnable {
            val result = service.textRequest(request)
            println("$tag text result $result")

        }).start()


    }
}