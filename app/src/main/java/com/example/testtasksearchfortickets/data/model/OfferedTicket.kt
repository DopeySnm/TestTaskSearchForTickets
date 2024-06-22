package com.example.testtasksearchfortickets.data.model

import com.example.testtasksearchfortickets.presenter.selectCountry.OfferedTicketUi

data class OfferedTicket(
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: Int
) {
    fun toOfferedTicketUi() = OfferedTicketUi(
        id,
        title,
        timeRange.joinToString(" "),
        price.toString()
    )
}
