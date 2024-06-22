package com.example.testtasksearchfortickets.data.api

import com.example.testtasksearchfortickets.data.api.models.OffersResponse
import com.example.testtasksearchfortickets.data.api.models.TicketsOffersResponse
import com.example.testtasksearchfortickets.data.api.models.TicketsResponse
import retrofit2.Response
import retrofit2.http.GET

interface TestAirTicketsService {

    @GET("v3/f9f903f7-6096-4161-9511-9e75713797d8/")
    suspend fun getAllOffers(): Response<OffersResponse>

    @GET("v3/38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a/")
    suspend fun getTicketsOffers(): Response<TicketsOffersResponse>

    @GET("v3/c0464573-5a13-45c9-89f8-717436748b69/")
    suspend fun getAllTickets(): Response<TicketsResponse>

}
