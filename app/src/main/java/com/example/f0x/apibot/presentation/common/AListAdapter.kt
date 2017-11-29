package com.example.f0x.apibot.presentation.common

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by f0x
 * on 29.03.17.
 */

abstract class AListAdapter<D , VH : RecyclerView.ViewHolder>() : RecyclerView.Adapter<VH>() {
    private var tag: String
    internal var onDataClickListener: ((item: D) -> Unit)? = null

    var dataSet: MutableList<D> = ArrayList<D>()
        @Synchronized
        set(value) {
            field = value
            isNeedShowEmptyView = dataSet.isEmpty()
            notifyDataSetChanged()
        }

    var isNeedShowEmptyView: Boolean = false
        private set

    init {
        this.dataSet = ArrayList()
        tag = this.javaClass.simpleName

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun add2DataSet(dataSet: MutableList<D>) {
        var needInvalidateList = false
        for (d in dataSet) {
            if (!this.dataSet.contains(d)) {
                this.dataSet.add(d)
                needInvalidateList = true
            }
        }
        if (needInvalidateList)
            notifyDataSetChanged()
    }

    @Synchronized
    fun clearData() {
        dataSet = ArrayList()
        isNeedShowEmptyView = true
        notifyDataSetChanged()
    }

    @Synchronized
    fun remove(data: D) {
        if (!dataSet.isEmpty()) {
            var index = -1
            for (i in dataSet.indices) {
                val d = dataSet[i]
                if (d == data)
                    index = i
            }
            if (index != -1) {
                dataSet.removeAt(index)
                notifyItemRemoved(index)
            }
        }
    }

    fun updateItemView(item: D) {
        notifyItemChanged(getItemPosition(item))
    }

    fun addItem(item: D) {
        if (!dataSet.contains(item)) {
            dataSet.add(item)
            notifyItemInserted(dataSet.size - 1)
        }
    }

    protected fun getItemPosition(item: D): Int {
        var position = -1
        for (i in dataSet.indices)
            if (item == dataSet[i])
                position = i
        return position
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        bindDefaultViewHolder(holder as DefaultViewHolder<D>, position)
    }


    private fun bindDefaultViewHolder(holder: DefaultViewHolder<D>, position: Int) {
        if (position >= dataSet.size)
            return
        val d = dataSet[position]
        holder.view.bind(d, View.OnClickListener {
            onDataClickListener?.invoke(d)
        })
    }

    fun onDestroy() {
        this.onDataClickListener = null
    }

    class DefaultViewHolder<D>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var view: IListItemView<D> = itemView as IListItemView<D>
    }
}
