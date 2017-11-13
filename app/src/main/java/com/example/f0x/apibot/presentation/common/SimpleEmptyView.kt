package com.example.f0x.apibot.presentation.common

import android.content.Context
import android.util.AttributeSet
import com.example.f0x.apibot.R
import kotlinx.android.synthetic.main.simple_empty_view.view.*

@Layout(id = R.layout.simple_empty_view)
class SimpleEmptyView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)

    : ABaseView(context, attrs, defStyleAttr), IEmptyView {

    override fun setText(text: Int) {
        tvNodDataFoundMessage.setText(text)
    }

}