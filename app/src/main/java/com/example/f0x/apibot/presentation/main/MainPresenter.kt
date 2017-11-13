package com.example.f0x.apibot.presentation.main

import ai.api.AIListener
import ai.api.android.AIService
import ai.api.model.AIError
import ai.api.model.AIResponse
import com.arellomobile.mvp.InjectViewState
import com.example.f0x.apibot.presentation.common.presenters.ABasePermissionPresenter
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by f0x on 14.11.17.
 */

@InjectViewState
@Singleton
class MainPresenter @Inject constructor(val aiService: AIService) : ABasePermissionPresenter<IMainView>(), AIListener {


    override fun onResult(result: AIResponse?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onListeningStarted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAudioLevel(level: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(error: AIError?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onListeningCanceled() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onListeningFinished() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onDestroy() {
        aiService.cancel()
        super.onDestroy()
    }


    override fun attachView(view: IMainView?) {
        super.attachView(view)
        aiService.startListening()
    }

    override fun detachView(view: IMainView) {
        aiService.pause()
        super.detachView(view)

    }
}