package com.example.testtasksearchfortickets.data.repository

import com.example.testtasksearchfortickets.data.api.OffersService
import com.example.testtasksearchfortickets.data.model.Offer
import com.example.testtasksearchfortickets.data.state.DataState
import javax.inject.Inject


class OffersRepositoryImpl @Inject constructor(
    private val service: OffersService
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
                } else DataState.Failure("Unable to get all foods")
            },
            onFailure = {
                return DataState.Failure(it.message ?: "Unknown error")
            }
        )
    }

}