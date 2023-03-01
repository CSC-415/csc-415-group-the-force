package com.example.jocasta.model

class Vehicle (
    val v_id : Int,
    val v_name : String,
    val v_model : String,
    val v_vehicle_class : String,
    val v_manufacturer : String,
    val v_length : String,
    val v_cost_in_credits : String,
    val v_crew : String,
    val passengers : String,
    val v_max_atmosphering_speed : String,
    val v_cargo_capacity : String,
    val v_consumables : String,
    val v_films : List<String>? = null,
    val v_pilots : List<String>? = null,
    val v_url : String
    )
