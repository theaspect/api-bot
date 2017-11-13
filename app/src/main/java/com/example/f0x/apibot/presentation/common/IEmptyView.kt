package com.example.f0x.apibot.presentation.common

import android.support.annotation.StringRes

/**
 * Created by f0x on 18.10.17.
 */

interface IEmptyView {
    fun setText(@StringRes text: Int)

    fun setVisibility(i: Int)
}
