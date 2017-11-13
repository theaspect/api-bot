package com.example.f0x.apibot.presentation.main

import android.view.ViewGroup
import com.example.f0x.apibot.domain.models.ai.AIModel
import com.example.f0x.apibot.presentation.common.AListAdapter

/**
 * Created by f0x on 14.11.17.
 */
class MainAdapter : AListAdapter<AIModel, AListAdapter.DefaultViewHolder<AIModel>>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DefaultViewHolder<AIModel> {
        return DefaultViewHolder(AIView(parent?.context!!))
    }
}