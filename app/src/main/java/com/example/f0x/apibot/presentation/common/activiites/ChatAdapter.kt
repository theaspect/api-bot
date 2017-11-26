package com.example.f0x.apibot.presentation.common.activiites

import android.view.ViewGroup
import com.example.f0x.apibot.presentation.common.AListAdapter
import com.example.f0x.apibot.presentation.common.presenters.IModel
import com.example.f0x.apibot.presentation.main.AIView
import com.example.f0x.apibot.presentation.main.UserView

/**
 * Created by f0x on 26.11.17.
 */
class ChatAdapter<D: IModel> : AListAdapter<D, AListAdapter.DefaultViewHolder<D>>() {
    private val aiType=1
    private val userType=2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder<D> {
        return if (viewType == aiType)
            DefaultViewHolder(AIView(parent.context))
        else
            DefaultViewHolder(UserView(parent.context))
    }

    override fun getItemViewType(position: Int): Int {
        return dataSet[position].type
    }
}