package com.example.jocasta.model

/**
 * A data class to represent a single character resource.
 *
 * @property id a unique datastore character identifier
 * @property name the character name
 * @property birthYear the character birth year using in-universe standard BBY or ABY
 * @property eyeColor the optional character eye color, default to "Unknown"
 * @property gender the optional character gender, default to "Unknown"
 * @property hairColor the optional character hair color, default to "Unknown"
 * @property height the character height in centimeters (cm)
 * @property mass the character mass in kilograms (kg)
 * @property skinColor the character skin color
 * @property homeWorld the character home world planet identifier
 * @property films the list of film identifiers this character has appeared in
 * @property species the list of species identifiers this character is classified beneath
 * @property starships the list of starship identifiers this character has piloted
 * @property vehicles the list of vehicle identifiers this character has piloted
 * @property source the source swapi.dev hypermedia resource URL
 */
data class Character(
    val id: Int,
    val name: String,
    val birthYear: String,
    val eyeColor: String? = "Unknown",
    val gender: String? = "Unknown",
    val hairColor: String? = "Unknown",
    val height: String,
    val mass: String,
    val skinColor: String,
    val homeWorld: Int? = null,
    val films: List<Int>? = null,
    val species: List<Int>? = null,
    val starships: List<Int>? = null,
    val vehicles: List<Int>? = null,
    val source: String
)
