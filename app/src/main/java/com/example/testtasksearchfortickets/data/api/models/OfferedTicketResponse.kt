package com.example.testtasksearchfortickets.data.api.models

import com.example.testtasksearchfortickets.data.model.OfferedTicket
import com.google.gson.annotations.SerializedName

data class TicketsOffersResponse(
    @SerializedName("tickets_offers") val ticketsOffers: List<OfferedTicketResponse>
)

data class OfferedTicketResponse(
    var id: Int,
    var title: String,
    @SerializedName("time_range") var timeRange: List<String>,
    var price: PriceResponse
) {
    fun toOfferedTicket() = OfferedTicket(
        id,
        title,
        timeRange,
        price.value
    )
}
