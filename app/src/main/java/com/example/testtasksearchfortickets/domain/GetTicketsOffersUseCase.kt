package com.example.testtasksearchfortickets.domain

import com.example.testtasksearchfortickets.data.model.OfferedTicket
import com.example.testtasksearchfortickets.data.repository.OffersRepository
import com.example.testtasksearchfortickets.data.state.DataState
import javax.inject.Inject

interface GetTicketsOffersUseCase {
    suspend operator fun invoke(): DataState<List<OfferedTicket>>
}

class GetTicketsOffersUseCaseImpl @Inject constructor(
    private val repository: OffersRepository
) : GetTicketsOffersUseCase {
    override suspend fun invoke(): DataState<List<OfferedTicket>> {
        return repository.getTicketsOffers()
    }

}
