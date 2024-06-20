package com.example.testtasksearchfortickets.presenter.mainScreen

import com.example.testtasksearchfortickets.R
import com.example.testtasksearchfortickets.databinding.OfferItemBinding
import com.example.testtasksearchfortickets.utils.PriceFormatUtils
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import java.text.DecimalFormat

object MainScreenDelegates {

    val offersHorizontalDelegate = adapterDelegateViewBinding<OfferUi, OfferUi, OfferItemBinding>(
        { layoutInflater, parent ->  OfferItemBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            when(item.id) {
                1 -> {
                    binding.coverImageView.setBackgroundResource(R.drawable.image_1)
                }
                2 -> {
                    binding.coverImageView.setBackgroundResource(R.drawable.image_2)
                }
                3 -> {
                    binding.coverImageView.setBackgroundResource(R.drawable.image_3)
                }
            }
            val price = binding.root.resources.getString(R.string.from) +
                    " ${PriceFormatUtils.priceFormat(item.price)} " +
                    binding.root.resources.getString(R.string.currency)
            binding.nameTextView.text = item.title
            binding.townTextView.text = item.town
            binding.priceTextView.text = price
        }
    }
}
