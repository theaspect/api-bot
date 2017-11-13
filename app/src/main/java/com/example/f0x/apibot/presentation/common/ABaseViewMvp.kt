package com.example.f0x.apibot.presentation.common

import android.content.Context
import android.util.AttributeSet
import com.arellomobile.mvp.MvpDelegate
import com.arellomobile.mvp.MvpView

/**
 * Created by f0x on 07.10.17.
 */

abstract class ABaseViewMvp @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ABaseView(context, attrs, defStyleAttr), MvpView {


    private var mvpDelegate: MvpDelegate<MvpView> = MvpDelegate(this as MvpView)

    var parentDelegate: MvpDelegate<MvpView>? = null
        set(value) {
            field = value
            mvpDelegate.setParentDelegate(parentDelegate, id.toString())
        }

    init {
        inject()
    }

    override fun onDetachedFromWindow() {
        mvpDelegate.onSaveInstanceState()
        mvpDelegate.onDetach()
        super.onDetachedFromWindow()
    }

    protected abstract fun inject()
}
