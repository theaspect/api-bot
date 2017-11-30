package com.example.f0x.apibot.presentation.common.activiites

import android.os.Bundle
import android.support.annotation.StringRes
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.f0x.apibot.R
import com.example.f0x.apibot.presentation.common.Layout

/**
 * Created by f0x on 26.10.17.
 */
abstract class ABaseActivity : MvpAppCompatActivity() {

    internal val tag = this.javaClass.simpleName
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        val layout = javaClass.getAnnotation(Layout::class.java) as Layout
        setContentView(layout.id)
        progressBar = findViewById<ProgressBar>(R.id.prBar)
        initViews()
        initAnimations()
        initListeners()
    }

    protected open fun initViews() {}

    protected open fun initAnimations() {}

    protected open fun initListeners() {}

    fun showLoadingProgress(show: Boolean) {
        if (show)
            progressBar?.visibility = VISIBLE
        else
            progressBar?.visibility = INVISIBLE

    }

    fun showToast(@StringRes messageStringResId: Int) =
            Toast.makeText(this, messageStringResId, Toast.LENGTH_SHORT).show()

    fun showToast(message: String?) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


    abstract fun inject()
}