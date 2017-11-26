package com.example.f0x.apibot.presentation.main

import android.content.Context
import android.util.AttributeSet
import com.example.f0x.apibot.R
import com.example.f0x.apibot.domain.models.ai.UserModel
import com.example.f0x.apibot.presentation.common.ABaseView
import com.example.f0x.apibot.presentation.common.IListItemView
import com.example.f0x.apibot.presentation.common.Layout
import kotlinx.android.synthetic.main.item_ai.view.*

/**
 * Created by f0x on 26.11.17.
 */
@Layout(id = R.layout.item_user)
class UserView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ABaseView(context, attrs, defStyleAttr), IListItemView<UserModel> {


    override fun bind(item: UserModel, onClickListener: OnClickListener) {
        tvTextResponse.text = item.query
    }

}