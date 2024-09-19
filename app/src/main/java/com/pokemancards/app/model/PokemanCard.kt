package com.pokemancards.app.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.Serial

@Parcelize
@Serializable
data class PokemanCard(
    @SerialName("id")
    val id: String,
    @SerialName("images")
    val images: Images,
    @SerialName("name")
    val name: String,
    @SerialName("types")
    val types: List<String>,
    @SerialName("level")
    var level: String? = null,
    @SerialName("hp")
    val hp: String,
    @SerialName("subtypes")
    val subTypes: List<String>?,
    @SerialName("attacks")
    val attacks: List<Attack>?,
    @SerialName("abilities")
    val abilities: List<Ability>? = null,
    @SerialName("weaknesses")
    val weaknesses: List<Weakness>?,
    @SerialName("resistances")
    val resistances: List<Resistance>? = null,
): Parcelable

@Parcelize
@Serializable
data class Images(
    @SerialName("small")
    val small: String,
    @SerialName("large")
    val large: String,
): Parcelable

@Parcelize
@Serializable
data class Attack(
    @SerialName("name")
    val name: String,
    @SerialName("cost")
    val cost: List<String>,
    @SerialName("convertedEnergyCost")
    val convertedEnergyCost: Int,
    @SerialName("damage")
    val damage: String,
    @SerialName("text")
    val text: String,
): Parcelable

@Parcelize
@Serializable
data class Weakness(
    @SerialName("type")
    val type: String,
    @SerialName("value")
    val value: String
): Parcelable

@Parcelize
@Serializable
data class Ability(
    @SerialName("type")
    val type: String,
    @SerialName("name")
    val name: String,
    @SerialName("text")
    val text: String
):Parcelable


@Parcelize
@Serializable
data class Resistance(
    @SerialName("type")
    val type: String,
    @SerialName("value")
    val value: String
): Parcelable
