package com.example.jocasta.data.model

import com.example.jocasta.utility.DeserializeInt
import com.example.jocasta.utility.DeserializeIntList
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
data class Vehicle(
    @SerializedName("vehicle_class")
    val vehicleClass: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("model")
    val model: String,

    @SerializedName("manufacturer")
    val manufacturer: String,

    @SerializedName("cost_in_credits")
    val costInCredits: String,

    @SerializedName("length")
    val length: String,

    @SerializedName("max_atmosphering_speed")
    val maxAtmospheringSpeed: String,

    @SerializedName("crew")
    val crew: String,

    @SerializedName("passengers")
    val passengers: String,

    @SerializedName("cargo_capacity")
    val cargoCapacity: String,

    @SerializedName("consumables")
    val consumables: String,

    @SerializedName("films")
    @field:JsonAdapter(DeserializeIntList::class)
    val films: List<Int>,

    @SerializedName("pilots")
    @field:JsonAdapter(DeserializeIntList::class)
    val pilots: List<Int>,

    @SerializedName("created")
    val created: String,

    @SerializedName("edited")
    val edited: String,

    @SerializedName("url")
    @JsonAdapter(DeserializeInt::class)
    val id: Int,
) : Resource

data class VehicleSet(
    @field:SerializedName("count")
    var count: Int,

    @field:SerializedName("next")
    @field:JsonAdapter(DeserializeInt::class)
    var next: Int,

    @field:SerializedName("previous")
    @field:JsonAdapter(DeserializeInt::class)
    var previous: Int,


    @field:SerializedName("results")
    var vehicles: List<Vehicle>
):ResourceSet

val DefaultVehicleSet = VehicleSet(
    count = 0,
    next = 1,
    previous = -1,
    vehicles = emptyList()
)