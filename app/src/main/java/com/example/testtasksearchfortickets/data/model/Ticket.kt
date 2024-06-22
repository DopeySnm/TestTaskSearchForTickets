package com.example.testtasksearchfortickets.data.model

import com.example.testtasksearchfortickets.data.api.models.Arrival
import com.example.testtasksearchfortickets.data.api.models.Departure
import com.example.testtasksearchfortickets.presenter.allTickets.TicketUi
import com.example.testtasksearchfortickets.utils.DateUtils

data class Ticket(
    val id: Int,
    val badge: String?,
    val price: Int,
    val departure: Departure,
    var arrival: Arrival,
    val transfer: Boolean,
) {
    fun toTicketUi() = TicketUi(
        id,
        badge,
        price.toString(),
        departure.airport,
        DateUtils.getTime(departure.date),
        arrival.airport,
        DateUtils.getTime(arrival.date),
        DateUtils.getFlightTime(departure.date, arrival.date),
        transfer
    )
}
