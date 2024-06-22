package com.example.testtasksearchfortickets.presenter.allTickets

data class TicketUi(
    val id: Int,
    val badge: String?,
    val price: String,
    val departureAirport: String,
    val departureDate: String,
    var arrivalAirport: String,
    val arrivalDate: String,
    val flightTime: String,
    val transfer: Boolean,
)
