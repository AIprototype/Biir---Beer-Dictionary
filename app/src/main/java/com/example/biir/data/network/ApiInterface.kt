package com.example.biir.data.network

import com.example.biir.data.network.models.response.BeerListDetailResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("beers")
    suspend fun getBeerData(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<ArrayList<BeerListDetailResponseModel>>
}