package com.example.f0x.apibot.presentation.common

import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by f0x on 29.10.17.
 */

interface IListView<D> : IView {
    @StateStrategyType(SingleStateStrategy::class)
    fun setData(dataSet: MutableList<D>)


    @StateStrategyType(SingleStateStrategy::class)
    fun clearData()


    fun updateItemView(item: D)

}
