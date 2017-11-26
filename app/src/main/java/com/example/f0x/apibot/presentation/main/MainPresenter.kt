package com.example.f0x.apibot.presentation.main

import ai.api.AIListener
import ai.api.android.AIService
import ai.api.model.AIError
import ai.api.model.AIRequest
import ai.api.model.AIResponse
import android.os.Build
import com.arellomobile.mvp.InjectViewState
import com.example.f0x.apibot.presentation.common.presenters.ABasePermissionPresenter
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by f0x on 14.11.17.
 */

@InjectViewState
@Singleton
class MainPresenter @Inject constructor(val service: AIService) : ABasePermissionPresenter<IMainView>(), AIListener {
    var isListening = false
    var isPaused = false
    var isAudioAccepted = false


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        isAudioAccepted = checkRecordAudioPermission()
        if (!isAudioAccepted && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            requestRecordAudioPermission()
    }

    init {
        service.setListener(this)
    }

    override fun onResult(result: AIResponse?) {
        println("$tag onResult: " + result?.result?.resolvedQuery)
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

    override fun attachView(view: IMainView?) {
        if (isListening && isPaused) {
            service.resume()
            isPaused = false
        }
        super.attachView(view)
    }

    override fun onDestroy() {
        service.cancel()
        super.onDestroy()
    }

    override fun detachView(view: IMainView) {
        if (isListening) {
            service.pause()
            isPaused = true
        }
        super.detachView(view)

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