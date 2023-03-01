package com.example.jocasta.model

data class Starship(
    val s_id: Int,
    val s_name: String,
    val s_model: String,
    val s_class: String,
    val s_manufacturer: String,
    val s_length: String,
    val s_cost_in_credits: String,
    val s_crew : String,
    val s_passengers: String,
    val s_max_atmosphering_speed : String,
    val s_cargo_capacity: String,
    val s_consumables :String,         //In the Star wars API it said "*string" assuming that means it is the max of time the vehicle has
    val s_films: List<String>? = null,
    val s_pilots: List<String>? = null,
    val s_url: String

)