package com.example.jocasta.model

data class Planet(
    val name: String,
    val rotationPeriod: Int,
    val orbitalPeriod: Int,
    val diameter: Int,
    val climate: String,
    val gravity: String,
    val terrain: String,
    val surfaceWater: Int,
    val population: Int,
    val residents: List<String>? = null,
    val films: List<String>? = null
)
