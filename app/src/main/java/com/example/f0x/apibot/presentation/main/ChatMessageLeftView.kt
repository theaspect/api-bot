package com.example.f0x.apibot.presentation.main

import android.content.Context
import android.util.AttributeSet
import com.example.f0x.apibot.R
import com.example.f0x.apibot.presentation.common.ABaseView
import com.example.f0x.apibot.presentation.common.IListItemView
import com.example.f0x.apibot.presentation.common.Layout
import kotlinx.android.synthetic.main.item_left.view.*

/**
 * Created by f0x on 14.11.17.
 */
@Layout(id = R.layout.item_left)
class ChatMessageLeftView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ABaseView(context, attrs, defStyleAttr), IListItemView<IChatItem> {

    override fun bind(item: IChatItem, onClickListener: OnClickListener) {
        // TODO extract resource
        tvTextResponse.text = if (item.getText().isEmpty()) "I’m sorry, I can’t find any information on that right now." else item.getText()
        llBubble.setOnClickListener(onClickListener)
    }

}