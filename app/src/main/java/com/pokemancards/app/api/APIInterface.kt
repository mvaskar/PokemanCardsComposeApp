package com.pokemancards.app.api

import com.pokemancards.app.model.APIResponse
import com.pokemancards.app.model.PokemanCard
import retrofit2.http.*

interface APIInterface {
    @GET("cards")
    suspend fun getCards(
        @Query("pageSize") pageSize: Int = 20
    ): APIResponse<List<PokemanCard>>
}