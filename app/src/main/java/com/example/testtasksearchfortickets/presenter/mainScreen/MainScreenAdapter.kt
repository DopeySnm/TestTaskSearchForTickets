package com.example.testtasksearchfortickets.presenter.mainScreen

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MainScreenAdapter : AsyncListDifferDelegationAdapter<OfferUi>(DiffUtil) {

    init {
        delegatesManager.addDelegate(MainScreenDelegates.offersHorizontalDelegate)
    }

    companion object {
        val DiffUtil = object : DiffUtil.ItemCallback<OfferUi>() {
            override fun areItemsTheSame(oldItem: OfferUi, newItem: OfferUi): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: OfferUi, newItem: OfferUi): Boolean {
                return oldItem == newItem
            }
        }
    }
}
