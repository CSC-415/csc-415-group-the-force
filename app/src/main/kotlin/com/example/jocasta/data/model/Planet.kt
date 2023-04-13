package com.example.jocasta.data.model


import com.example.jocasta.utility.DeserializeInt
import com.example.jocasta.utility.DeserializeIntList
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName


val DefaultPlanetSet = PlanetSet(
    count = 0,
    next = 1,
    previous = -1,
    planets = emptyList()
)

data class PlanetSet(
    @SerializedName("count")
    var count: Int,
    @field:JsonAdapter(DeserializeInt::class)
    var next: Int,
    @field:JsonAdapter(DeserializeInt::class)
    var previous: Int,
    @SerializedName("results")
    var planets: List<Planet>
) : ResourceSet

data class Planet(
    @JsonAdapter(DeserializeInt::class)
    val id: Int,
    @SerializedName("climate")
    val climate: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("diameter")
    val diameter: String,
    @SerializedName("edited")
    val edited: String,
    @SerializedName("films")
    @field:JsonAdapter(DeserializeIntList::class)
    val films: List<Int>,
    @SerializedName("gravity")
    val gravity: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("orbital_period")
    val orbitalPeriod: String,
    @SerializedName("population")
    val population: String,
    @SerializedName("residents")
    @field:JsonAdapter(DeserializeIntList::class)
    val people: List<Int>,
    @SerializedName("rotation_period")
    val rotationPeriod: String,
    @SerializedName("surface_water")
    val surfaceWater: String,
    @SerializedName("terrain")
    val terrain: String,
    @SerializedName("url")
    val url: String
)
