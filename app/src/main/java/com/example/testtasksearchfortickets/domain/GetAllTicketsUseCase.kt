package com.example.testtasksearchfortickets.domain

import com.example.testtasksearchfortickets.data.model.Ticket
import com.example.testtasksearchfortickets.data.repository.OffersRepository
import com.example.testtasksearchfortickets.data.state.DataState
import javax.inject.Inject

interface GetAllTicketsUseCase {
    suspend operator fun invoke(): DataState<List<Ticket>>
}

class GetAllTicketsUseCaseImpl @Inject constructor(
    private val repository: OffersRepository
): GetAllTicketsUseCase {
    override suspend fun invoke(): DataState<List<Ticket>> {
        return repository.getAllTickets()
    }
}
