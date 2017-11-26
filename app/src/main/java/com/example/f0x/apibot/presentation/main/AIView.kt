package com.example.f0x.apibot.presentation.main

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import com.example.f0x.apibot.R
import com.example.f0x.apibot.domain.models.ai.AIModel
import com.example.f0x.apibot.presentation.common.ABaseView
import com.example.f0x.apibot.presentation.common.IListItemView
import com.example.f0x.apibot.presentation.common.Layout
import kotlinx.android.synthetic.main.item_ai.view.*

/**
 * Created by f0x on 14.11.17.
 */
@Layout(id = R.layout.item_ai)
class AIView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ABaseView(context, attrs, defStyleAttr), IListItemView<AIModel> {

    override fun bind(item: AIModel, onClickListener: OnClickListener) {

        if (item.error != null) {
            tvTextResponse.text = item.error.message
            tvTextResponse.setTextColor(Color.RED)
            return
        }
        tvTextResponse.setTextColor(Color.DKGRAY)
        tvTextResponse.text = item.response?.result?.resolvedQuery
    }

}