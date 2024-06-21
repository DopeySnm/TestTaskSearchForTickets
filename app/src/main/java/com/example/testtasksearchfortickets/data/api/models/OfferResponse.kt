package com.example.testtasksearchfortickets.data.api.models

import com.example.testtasksearchfortickets.data.model.Offer

data class OffersResponse(
    var offers: List<OfferResponse>
)

data class OfferResponse(
    var id: Int,
    var title: String,
    var town: String,
    var price: PriceResponse
) {
    fun toOffer() = Offer(
        id = id,
        title = title,
        town = town,
        price = price.value
    )
}

data class PriceResponse(
    var value: Int
)
