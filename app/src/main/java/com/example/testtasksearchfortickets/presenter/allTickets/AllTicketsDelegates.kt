package com.example.testtasksearchfortickets.presenter.allTickets

import com.example.testtasksearchfortickets.R
import com.example.testtasksearchfortickets.databinding.TicketItemBinding
import com.example.testtasksearchfortickets.databinding.TicketWithBadgeItemBinding
import com.example.testtasksearchfortickets.utils.PriceFormatUtils
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object AllTicketsDelegates {

    val ticketsWithBadgeDelegate = adapterDelegateViewBinding<TicketUi, TicketUi, TicketWithBadgeItemBinding>(
        { layoutInflater, parent ->  TicketWithBadgeItemBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            binding.badgeNameTextView.text = item.badge
            binding.ticketItemContainer.apply {
                val transfers = if (item.transfer) {
                    binding.root.context.getString(R.string.with_transfers)
                } else {
                    binding.root.context.getString(R.string.no_transfers)
                }
                val price = PriceFormatUtils.priceFormat(item.price)
                val priceWithCurrency = price + " ${binding.root.context.getString(R.string.currency_ruble)}"
                val flightTime = item.flightTime + "${binding.root.context.getString(R.string.hour)} / $transfers"
                priceTextView.text = priceWithCurrency
                timeTextView.text = flightTime
                arrivalTimeTextView.text = item.arrivalDate
                arrivalAirportCodeTextView.text = item.arrivalAirport
                departureTimeTextView.text = item.departureDate
                departureAirportCodeTextView.text = item.departureAirport
            }
        }
    }

    val ticketsDelegate = adapterDelegateViewBinding<TicketUi, TicketUi, TicketItemBinding>(
        { layoutInflater, parent ->  TicketItemBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            val transfers = if (item.transfer) {
                binding.root.context.getString(R.string.with_transfers)
            } else {
                binding.root.context.getString(R.string.no_transfers)
            }
            val price = PriceFormatUtils.priceFormat(item.price)
            val priceWithCurrency = price + " ${binding.root.context.getString(R.string.currency_ruble)}"
            val flightTime = item.flightTime + "${binding.root.context.getString(R.string.hour)} / $transfers"
            binding.priceTextView.text = priceWithCurrency
            binding.timeTextView.text = flightTime
            binding.arrivalTimeTextView.text = item.arrivalDate
            binding.arrivalAirportCodeTextView.text = item.arrivalAirport
            binding.departureTimeTextView.text = item.departureDate
            binding.departureAirportCodeTextView.text = item.departureAirport
        }
    }
}
