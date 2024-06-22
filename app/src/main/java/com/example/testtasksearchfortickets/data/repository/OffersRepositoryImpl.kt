package com.example.testtasksearchfortickets.data.repository

import com.example.testtasksearchfortickets.data.api.TestAirTicketsService
import com.example.testtasksearchfortickets.data.model.Offer
import com.example.testtasksearchfortickets.data.model.OfferedTicket
import com.example.testtasksearchfortickets.data.model.Ticket
import com.example.testtasksearchfortickets.data.state.DataState
import javax.inject.Inject


class OffersRepositoryImpl @Inject constructor(
    private val service: TestAirTicketsService
) : OffersRepository {

    override suspend fun getAllOffers(): DataState<List<Offer>> {
        kotlin.runCatching {
            service.getAllOffers()
        }.fold(
            onSuccess = { response ->
                return if (response.isSuccessful) {
                    response.body()?.let { offers ->
                        val result = offers.offers.map {
                            it.toOffer()
                        }
                        DataState.Success(result)
                    } ?: DataState.Failure("Empty response")
                } else DataState.Failure("Unable to get all offers")
            },
            onFailure = {
                return DataState.Failure(it.message ?: "Unknown error")
            }
        )
    }


    override suspend fun getTicketsOffers(): DataState<List<OfferedTicket>> {
        kotlin.runCatching {
            service.getTicketsOffers()
        }.fold(
            onSuccess = { response ->
                return if (response.isSuccessful) {
                    response.body()?.let { ticketsOffers ->
                        val result = ticketsOffers.ticketsOffers.map {
                            it.toOfferedTicket()
                        }
                        DataState.Success(result)
                    } ?: DataState.Failure("Empty response")
                } else DataState.Failure("Unable to get tickets offers")
            },
            onFailure = {
                return DataState.Failure(it.message ?: "Unknown error")
            }
        )
    }

    override suspend fun getAllTickets(): DataState<List<Ticket>> {
        kotlin.runCatching {
            service.getAllTickets()
        }.fold(
            onSuccess = { response ->
                return if (response.isSuccessful) {
                    response.body()?.let { tickets ->
                        val result = tickets.tickets.map {
                            it.toTicket()
                        }
                        DataState.Success(result)
                    } ?: DataState.Failure("Empty response")
                } else DataState.Failure("Unable to get all tickets")
            },
            onFailure = {
                return DataState.Failure(it.message ?: "Unknown error")
            }
        )
    }

}
