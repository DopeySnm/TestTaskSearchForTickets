package com.example.testtasksearchfortickets.data.api.models

import com.example.testtasksearchfortickets.data.model.Ticket
import com.google.gson.annotations.SerializedName

data class TicketsResponse(
    val tickets: List<TicketResponse>
)

data class TicketResponse(
    var id: Int,
    var badge: String?,
    var price: PriceResponse,
    @SerializedName("provider_name") var providerName: String,
    var company: String? = null,
    var departure: Departure,
    var arrival: Arrival,
    @SerializedName("has_transfer") var hasTransfer: Boolean,
    @SerializedName("has_visa_transfer") var hasVisaTransfer: Boolean,
    var luggage: Luggage,
    @SerializedName("hand_luggage") var handLuggage: HandLuggage,
    @SerializedName("is_returnable") var isReturnable: Boolean,
    @SerializedName("is_exchangable") var isExchangable: Boolean
) {
    fun toTicket() = Ticket(
        id = id,
        badge = badge,
        price = price.value,
        departure = departure,
        arrival = arrival,
        transfer =  hasTransfer
    )
}

data class Departure(
    var town: String,
    var date: String,
    var airport: String
)

data class Arrival(
    var town: String,
    var date: String,
    var airport: String
)

data class Luggage(
    @SerializedName("has_luggage") var hasLuggage: Boolean,
    var price: PriceResponse
)

data class HandLuggage(
    @SerializedName("has_hand_luggage") var hasHandLuggage: Boolean,
    var size: String
)
