package com.example.testtasksearchfortickets.presenter.allTickets

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class AllTicketsAdapter : AsyncListDifferDelegationAdapter<TicketUi>(DiffUtil) {

    init {
        delegatesManager.addDelegate(1, AllTicketsDelegates.ticketsDelegate)
        delegatesManager.addDelegate(2, AllTicketsDelegates.ticketsWithBadgeDelegate)
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].badge == null) {
            1
        } else {
            2
        }
    }


    companion object {
        val DiffUtil = object : DiffUtil.ItemCallback<TicketUi>() {
            override fun areItemsTheSame(oldItem: TicketUi, newItem: TicketUi): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TicketUi, newItem: TicketUi): Boolean {
                return oldItem == newItem
            }
        }
    }

}
