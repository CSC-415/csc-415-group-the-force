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
    @field:SerializedName("count")
    var count: Int,
    @field:JsonAdapter(DeserializeInt::class)
    var next: Int,
    @field:JsonAdapter(DeserializeInt::class)
    var previous: Int,
    @field:SerializedName("results")
    var planets: List<Planet>
) : ResourceSet

data class Planet(
    @SerializedName("url")
    @field:JsonAdapter(DeserializeInt::class)
    val id: Int,
    @field:SerializedName("climate")
    val climate: String,
    @field:SerializedName("created")
    val created: String,
    @field:SerializedName("diameter")
    val diameter: String,
    @field:SerializedName("edited")
    val edited: String,
    @field:SerializedName("films")
    @field:JsonAdapter(DeserializeIntList::class)
    val films: List<Int>,
    @field:SerializedName("gravity")
    val gravity: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("orbital_period")
    val orbitalPeriod: String,
    @field:SerializedName("population")
    val population: String,
    @field:SerializedName("residents")
    @field:JsonAdapter(DeserializeIntList::class)
    val people: List<Int>,
    @field:SerializedName("rotation_period")
    val rotationPeriod: String,
    @field:SerializedName("surface_water")
    val surfaceWater: String,
    @field:SerializedName("terrain")
    val terrain: String
) : Resource
