package com.example.f0x.apibot.presentation.common.activiites

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.StringRes
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.f0x.apibot.R
import com.example.f0x.apibot.presentation.common.AListAdapter
import com.example.f0x.apibot.presentation.common.EmptyRecyclerView
import com.example.f0x.apibot.presentation.common.IEmptyView
import com.example.f0x.apibot.presentation.common.IListView

/**
 * Created by f0x on 13.11.17.
 */
abstract class ABaseListActivity<D, VH : RecyclerView.ViewHolder> : ABaseSwipeActivity(), IListView<D> {
    lateinit var recyclerView: EmptyRecyclerView
    protected var emptyView: IEmptyView? = null
    protected lateinit var adapter: AListAdapter<D, VH>
    protected lateinit var layoutManager: RecyclerView.LayoutManager
    protected var isDoScrollDown = true

    val isShowDivider: Boolean
        get() = true

    val dividerDecoration: RecyclerView.ItemDecoration
        get() = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

    @get:StringRes
    protected abstract val emptyViewText: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView = findViewById(R.id.rvList)
        emptyView = findViewById<View>(R.id.emptyView) as IEmptyView

        recyclerView.setEmptyView(emptyView, emptyViewText)
        recyclerView.setHasFixedSize(true)
        if (isShowDivider)
            recyclerView.addItemDecoration(dividerDecoration)
        recyclerView.itemAnimator = DefaultItemAnimator()
        layoutManager = initLayoutManager()
        recyclerView.layoutManager = layoutManager
        adapter = initAdapter()
        recyclerView.setAdapter(adapter)
        initListListener()
    }

    private fun initListListener() {
        super.initListeners()
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val first = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if (first <= adapter.dataSet.size)
                    return

                onFirstVisible(adapter.dataSet[first])
                if (isDoScrollDown && dy > 0) {
                    if (layoutManager.childCount + first >= layoutManager.itemCount - 2) {
                        onScrollDown()
                    }
                }
            }
        })
    }


    override fun clearData() {
        adapter.clearData()
    }

    override fun setData(dataSet: MutableList<D>) {
        adapter.dataSet = dataSet
    }


    override fun updateItemView(item: D) {
        adapter.updateItemView(item)
    }

    @CallSuper
    protected fun onScrollDown() {
        isDoScrollDown = false
    }

    @CallSuper
    protected fun releaseScroll() {
        isDoScrollDown = true
    }

    protected fun onFirstVisible(item: D?) {

    }

    override fun stopRefreshing() {
        swipeToRefreshLayout.isRefreshing = false
    }

    protected abstract fun initAdapter(): AListAdapter<D, VH>

    protected abstract fun initLayoutManager(): RecyclerView.LayoutManager
}