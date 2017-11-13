package com.example.f0x.apibot.presentation.common

import android.view.View

/**
 * Created by f0x on 16.07.17.
 */

interface IListItemView<D> {
    fun bind(item: D, onClickListener: View.OnClickListener)
}
