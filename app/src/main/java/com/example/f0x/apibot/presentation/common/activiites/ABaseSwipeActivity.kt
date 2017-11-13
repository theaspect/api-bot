package com.example.f0x.apibot.presentation.common.activiites

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import com.example.f0x.apibot.R

/**
 * Created by f0x on 29.10.17.
 */

abstract class ABaseSwipeActivity : ABaseActivity(), SwipeRefreshLayout.OnRefreshListener {
    protected lateinit var swipeToRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        swipeToRefreshLayout = findViewById(R.id.swipeToRefresh)
        swipeToRefreshLayout.setOnRefreshListener(this)

    }
}

