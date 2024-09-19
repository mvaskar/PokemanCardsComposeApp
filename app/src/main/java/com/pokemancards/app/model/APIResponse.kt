package com.pokemancards.app.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APIResponse<T>(
    @SerialName("data")
    val data: T,
    @SerialName("page")
    val page: Int,
    @SerialName("pageSize")
    val pageSize: Int,
    @SerialName("count")
    val count: Int,
    @SerialName("totalCount")
    val totalCount: Int,
)