package com.example.f0x.apibot.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * Created by f0x on 28.10.17.
 */

abstract class ABaseView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {

    init {
        val layout = javaClass.getAnnotation(Layout::class.java) as Layout
        inflate(context, layout.id, this)

    }
}
