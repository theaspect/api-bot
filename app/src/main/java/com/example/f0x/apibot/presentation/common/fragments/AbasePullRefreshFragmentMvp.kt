package com.example.f0x.apibot.presentation.common.fragments

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import com.example.f0x.apibot.R

/**
 * Created by f0x on 13.11.17.
 */
abstract class AbasePullRefreshFragmentMvp : ABaseFragmentMvp(), SwipeRefreshLayout.OnRefreshListener {
    protected lateinit var swipeToRefreshLayout: SwipeRefreshLayout

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeToRefreshLayout = view!!.findViewById(R.id.swipeToRefresh)
        swipeToRefreshLayout.setOnRefreshListener(this)
    }


    fun stopRefreshing() {
        swipeToRefreshLayout.isRefreshing = false
    }

}
