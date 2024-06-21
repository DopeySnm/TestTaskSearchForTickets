package com.example.testtasksearchfortickets.data.repository

import com.example.testtasksearchfortickets.data.model.Offer
import com.example.testtasksearchfortickets.data.state.DataState

interface OffersRepository {

    suspend fun getAllOffers(): DataState<List<Offer>>
    
}