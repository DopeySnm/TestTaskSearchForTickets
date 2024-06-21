package com.example.testtasksearchfortickets.domain

import com.example.testtasksearchfortickets.data.model.Offer
import com.example.testtasksearchfortickets.data.repository.OffersRepository
import com.example.testtasksearchfortickets.data.state.DataState
import javax.inject.Inject

interface GetAllOffersUseCase {
    suspend operator fun invoke(): DataState<List<Offer>>
}

class GetAllOffersUseCaseImpl @Inject constructor(
    private val repository: OffersRepository
) : GetAllOffersUseCase {
    override suspend fun invoke(): DataState<List<Offer>> {
       return repository.getAllOffers()
    }

}
