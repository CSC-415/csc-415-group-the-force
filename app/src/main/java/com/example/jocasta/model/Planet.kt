package com.example.jocasta.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Planet(
    val name: String,
    @SerialName("rotation_period")
    val rotationPeriod: Int,
    @SerialName("orbital_period")
    val orbitalPeriod: Int,
    val diameter: Int,
    val climate: String,
    val gravity: String,
    val terrain: String,
    @SerialName("surface_water")
    val surfaceWater: String,
    val population: String,
    val residents: List<String>,
    val films: List<String>,
    @SerialName("url")
    val source: String
)
