package com.example.jocasta.model

data class Species(
    val name: String,
    val classification: String,
    val designation: String,
    val averageHeight: Int,
    val skinColors: List<String>? = null,
    val hairColors: List<String>? = null,
    val averageLifespan: Int,
    val homeWorld: String,
    val language: String,
    val people: List<String>? = null,
    val films: List<String>? = null
)
