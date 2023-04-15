package com.example.jocasta.data.model

import com.example.jocasta.utility.DeserializeInt
import com.example.jocasta.utility.DeserializeIntList
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class Starship(
    @SerializedName("cargo_capacity")
    val cargoCapacity: String,
    @SerializedName("consumables")
    val consumables: String,
    @SerializedName("cost_in_credits")
    val costInCredits: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("crew")
    val crew: String,
    @SerializedName("edited")
    val edited: String,
    @field:SerializedName("films")
    @field:JsonAdapter(DeserializeIntList::class)
    val films: List<Int>,
    @SerializedName("hyperdrive_rating")
    val hyperdriveRating: String,
    @SerializedName("length")
    val length: String,
    @SerializedName("MGLT")
    val mGLT: String,
    @SerializedName("manufacturer")
    val manufacturer: String,
    @SerializedName("max_atmosphering_speed")
    val maxAtmospheringSpeed: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("passengers")
    val passengers: String,
    @SerializedName("pilots")
    val pilots: List<Any>,
    @SerializedName("starship_class")
    val starshipClass: String,
    @field:SerializedName("id")
    val id: Int,
): Resource

data class StarshipSet(
    var count: Int,

    @field:JsonAdapter(DeserializeInt::class)
    var next: Int,

    @field:JsonAdapter(DeserializeInt::class)
    var previous: Int,

    @field:SerializedName("results")
    var starships: List<Starship>
):ResourceSet

val DefaultStarshipSet = StarshipSet(
    count = 0,
    next = 1,
    previous = -1,
    starships = emptyList()
)