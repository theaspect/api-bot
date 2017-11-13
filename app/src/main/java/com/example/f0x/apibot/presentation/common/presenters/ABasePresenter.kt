package com.example.f0x.apibot.presentation.common.presenters

import android.content.Context
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * Created by f0x on 26.10.17.
 */
abstract class ABasePresenter<View : MvpView> : MvpPresenter<View>() {
    protected val tag = javaClass.simpleName
    lateinit var context: Context
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun detachView(view: View) {
        compositeDisposable.clear()
        super.detachView(view)
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

}