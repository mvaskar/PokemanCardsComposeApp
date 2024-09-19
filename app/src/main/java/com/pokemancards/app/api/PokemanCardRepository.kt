package com.pokemancards.app.api

import com.pokemancards.app.model.APIResponse
import com.pokemancards.app.model.PokemanCard
import javax.inject.Inject

class PokemanCardRepository @Inject constructor(
    private val apiInterface: APIInterface
) {

    suspend fun getCards(): APIResponse<List<PokemanCard>> {
        return apiInterface.getCards()
    }
}