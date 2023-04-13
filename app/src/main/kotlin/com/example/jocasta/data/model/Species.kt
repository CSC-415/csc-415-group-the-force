package com.example.jocasta.data.model

import com.example.jocasta.utility.DeserializeInt
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class SpeciesSet(
    @SerializedName("count")
    var count: Int,
    @SerializedName("next")
    @field:JsonAdapter(DeserializeInt::class)
    var next: Int,
    @SerializedName("previous")
    @field:JsonAdapter(DeserializeInt::class)
    var previous: Int,
    @SerializedName("results")
    var species: List<Species>
) : ResourceSet

val DefaultSpeciesSet = SpeciesSet(
    count = 0,
    next = 1,
    previous = -1,
    species = emptyList()
)

data class Species(
    @SerializedName("average_height")
    val averageHeight: String,
    @SerializedName("average_lifespan")
    val averageLifespan: String,
    @SerializedName("classification")
    val classification: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("designation")
    val designation: String,
    @SerializedName("edited")
    val edited: String,
    @SerializedName("eye_colors")
    val eyeColors: String,
    @SerializedName("films")
    @field:JsonAdapter(DeserializeInt::class)
    val films: List<Int>,
    @SerializedName("hair_colors")
    val hairColors: String,
    @SerializedName("homeworld")
    val homeWorld: String?,
    @SerializedName("language")
    val language: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("people")
    @field:JsonAdapter(DeserializeInt::class)
    val people: List<Int>,
    @SerializedName("skin_colors")
    val skinColors: String,
    @SerializedName("url")
    val url: String
)