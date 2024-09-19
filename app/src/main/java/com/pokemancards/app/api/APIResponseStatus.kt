package com.pokemancards.app.api

import com.pokemancards.app.model.APIResponse

sealed class APIResponseStatus<out T> {
    object Idle : APIResponseStatus<Nothing>()
    object Progress : APIResponseStatus<Nothing>()
    data class Success<T>(var response: APIResponse<T>) : APIResponseStatus<T>()
    data class Error(var error: String?) : APIResponseStatus<Nothing>()
}