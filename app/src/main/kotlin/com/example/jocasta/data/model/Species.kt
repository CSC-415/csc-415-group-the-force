package com.example.jocasta.data.model

import com.example.jocasta.utility.DeserializeInt
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class SpeciesSet(
    @field:SerializedName("count")
    var count: Int,
    @field:SerializedName("next")
    @field:JsonAdapter(DeserializeInt::class)
    var next: Int,
    @field:SerializedName("previous")
    @field:JsonAdapter(DeserializeInt::class)
    var previous: Int,
    @field:SerializedName("results")
    var species: List<Species>
) : ResourceSet

val DefaultSpeciesSet = SpeciesSet(
    count = 0,
    next = 1,
    previous = -1,
    species = emptyList()
)

data class Species(
    @SerializedName("url")
    @field:JsonAdapter(DeserializeInt::class)
    val id: Int,
    @field:SerializedName("average_height")
    val averageHeight: String,
    @field:SerializedName("average_lifespan")
    val averageLifespan: String,
    @field:SerializedName("classification")
    val classification: String,
    @field:SerializedName("created")
    val created: String,
    @field:SerializedName("designation")
    val designation: String,
    @field:SerializedName("edited")
    val edited: String,
    @field:SerializedName("eye_colors")
    val eyeColors: String,
    @field:SerializedName("films")
    @field:JsonAdapter(DeserializeInt::class)
    val films: List<Int>,
    @field:SerializedName("hair_colors")
    val hairColors: String,
    @field:SerializedName("homeworld")
    val homeWorld: String?,
    @field:SerializedName("language")
    val language: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("people")
    @field:JsonAdapter(DeserializeInt::class)
    val people: List<Int>,
    @field:SerializedName("skin_colors")
    val skinColors: String
) : Resource