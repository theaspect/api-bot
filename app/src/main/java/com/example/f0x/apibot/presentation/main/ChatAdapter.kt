package com.example.f0x.apibot.presentation.main

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.f0x.apibot.presentation.common.ABaseView
import com.example.f0x.apibot.presentation.common.AListAdapter

/**
 * Created by f0x on 26.11.17.
 */
class ChatAdapter : AListAdapter<IChatItem, AListAdapter.DefaultViewHolder<IChatItem>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder<IChatItem> {
        if (viewType == IChatItem.TYPE_BOT)
            return DefaultViewHolder(ChatMessageLeftView(parent.context))
        if (viewType == IChatItem.TYPE_USER)
            return DefaultViewHolder(ChatMessageRightView(parent.context))
        else
            return DefaultViewHolder(ChatDateView(parent.context))
    }

    override fun onBindViewHolder(holder: DefaultViewHolder<IChatItem>, position: Int) {
        super.onBindViewHolder(holder, position)
        (holder.view as ABaseView).layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun getItemViewType(position: Int): Int {
        return dataSet[position].getTypeItem()
    }
}