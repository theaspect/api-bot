package com.example.f0x.apibot.presentation.common

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by f0x on 26.10.17.
 */


interface IView : MvpView {


    @StateStrategyType(AddToEndStrategy::class)
    fun showLoadingProgress(show: Boolean)

    @StateStrategyType(AddToEndStrategy::class)
    fun showToast(@StringRes messageStringResId: Int)

    @StateStrategyType(SkipStrategy::class)
    fun showToast(message: String?)

}