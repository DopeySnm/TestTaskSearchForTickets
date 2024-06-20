package com.example.testtasksearchfortickets.data.api

import com.example.testtasksearchfortickets.data.api.models.OffersResponse
import retrofit2.Response
import retrofit2.http.GET

interface OffersService {

    @GET("v3/f9f903f7-6096-4161-9511-9e75713797d8/")
    suspend fun getAllOffers(): Response<OffersResponse>

}
