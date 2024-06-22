package com.example.testtasksearchfortickets.presenter.selectCountry

import com.example.testtasksearchfortickets.R
import com.example.testtasksearchfortickets.databinding.TicketsOffersItemBinding
import com.example.testtasksearchfortickets.utils.PriceFormatUtils
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object SelectCountryDelegates {

    val ticketsOffersHorizontalDelegate = adapterDelegateViewBinding<OfferedTicketUi, OfferedTicketUi, TicketsOffersItemBinding>(
        { layoutInflater, parent ->  TicketsOffersItemBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            when(item.id) {
                1 -> {
                    binding.statusImageView.setBackgroundResource(R.drawable.red_ball_icon)
                }
                10 -> {
                    binding.statusImageView.setBackgroundResource(R.drawable.blue_ball_icon)
                }
                30 -> {
                    binding.statusImageView.setBackgroundResource(R.drawable.white_ball_icon)
                }
            }
            binding.nameTextView.text = item.title
            binding.timeTextView.text = item.timeRange
            binding.priceTextView.text = "${PriceFormatUtils.priceFormat(item.price)} " +
                    binding.root.resources.getString(R.string.currency_ruble)
        }
    }

}
