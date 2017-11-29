package com.example.f0x.apibot.presentation.common

import android.content.Context
import android.support.annotation.StringRes
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View

/**
 * Created by f0x
 * on 28.03.17.
 */

open class EmptyRecyclerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var emptyView: IEmptyView? = null

    private val observer = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            checkIfEmpty()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }
    }
    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        val oldAdapter = getAdapter()
        oldAdapter?.unregisterAdapterDataObserver(observer)
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(observer)
        checkIfEmpty()
    }

    fun setEmptyView(emptyView: IEmptyView?, @StringRes text: Int) {
        this.emptyView = emptyView
        if (text != 0 && emptyView != null)
            emptyView.setText(text)
        checkIfEmpty()
    }

    internal fun checkIfEmpty() {
        if (emptyView == null || adapter == null)
            return
        var emptyViewVisible = adapter.itemCount == 0

        if (adapter is AListAdapter<*, *>) {
            if (emptyViewVisible) {
                emptyViewVisible = (adapter as AListAdapter<*, *>).isNeedShowEmptyView
            }
        }
        visibility = if (emptyViewVisible) {
            emptyView?.setVisibility(View.VISIBLE)
            View.GONE
        } else {
            emptyView?.setVisibility(View.GONE)
            View.VISIBLE
        }
    }

}
