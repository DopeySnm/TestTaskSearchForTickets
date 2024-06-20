package com.example.testtasksearchfortickets.data.model

import com.example.testtasksearchfortickets.presenter.mainScreen.OfferUi

data class Offer(
    val id: Int,
    val title: String,
    val town: String,
    val price: Int
) {
    fun toOfferUi() = OfferUi(
            id = id,
            title = title,
            town = town,
            price = price.toString()
        )

}
