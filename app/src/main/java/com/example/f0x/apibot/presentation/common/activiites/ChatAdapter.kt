package com.example.f0x.apibot.presentation.common.activiites

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.f0x.apibot.domain.models.ai.chat.ChatMessage
import com.example.f0x.apibot.presentation.common.ABaseView
import com.example.f0x.apibot.presentation.common.AListAdapter
import com.example.f0x.apibot.presentation.main.ChatMessageLeftView
import com.example.f0x.apibot.presentation.main.ChatMessageRightView

/**
 * Created by f0x on 26.11.17.
 */
class ChatAdapter : AListAdapter<ChatMessage, AListAdapter.DefaultViewHolder<ChatMessage>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder<ChatMessage> {
        return if (viewType == ChatMessage.TYPE_BOT)
            DefaultViewHolder(ChatMessageLeftView(parent.context))
        else
            DefaultViewHolder(ChatMessageRightView(parent.context))
    }

    override fun onBindViewHolder(holder: DefaultViewHolder<ChatMessage>, position: Int) {
        super.onBindViewHolder(holder, position)
        (holder.view as ABaseView).layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun getItemViewType(position: Int): Int {
        return dataSet[position].type
    }
}