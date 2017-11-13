package com.example.f0x.apibot.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment

/**
 * Created by f0x on 06.07.17.
 */

abstract class ABaseFragmentMvp : MvpAppCompatFragment() {
    internal val tag = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = javaClass.getAnnotation(Layout::class.java) as Layout
        return inflater?.inflate(layout.id, container, false)
    }


    fun showLoadingProgress(show: Boolean) {

    }

    fun showToast(messageStringResId: Int) {
        Toast.makeText(context, messageStringResId, Toast.LENGTH_SHORT).show()
    }

    fun showToast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    abstract fun inject()
}
