package com.example.jocasta.data.model

import com.example.jocasta.utility.DeserializeInt
import com.example.jocasta.utility.DeserializeIntList
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

/**
 * A [Resource] to represent an individual person or character within the cinematic universe.
 *
 * @property id a unique datastore person identifier
 * @property name the canonical name
 * @property birthYear the birth year using in-universe standard BBY or ABY
 * @property eyeColor the optional eye color, default to "Unknown"
 * @property gender the optional gender, default to "Unknown"
 * @property hairColor the optional hair color, default to "Unknown"
 * @property height the height in centimeters (cm)
 * @property mass the mass in kilograms (kg)
 * @property skinColor the skin color
 * @property homeWorld the home world planet identifier
 * @property films the list of film identifiers this person has appeared in
 * @property species the list of species identifiers this person is classified beneath
 * @property starships the list of starship identifiers this person has piloted
 * @property vehicles the list of vehicle identifiers this person has piloted
 */
data class Person(
    @SerializedName("url")
    @JsonAdapter(DeserializeInt::class)
    val id: Int,

    val name: String,

    @field:SerializedName("birth_year")
    val birthYear: String,

    @field:SerializedName("eye_color")
    val eyeColor: String,

    val gender: String,

    @field:SerializedName("hair_color")
    val hairColor: String,

    @field:JsonAdapter(DeserializeInt::class)
    val height: Int,

    @field:JsonAdapter(DeserializeInt::class)
    val mass: Int,

    @field:SerializedName("skin_color")
    val skinColor: String,

    @field:SerializedName("homeworld")
    @field:JsonAdapter(DeserializeInt::class)
    val homeWorld: Int,

    @field:JsonAdapter(DeserializeIntList::class)
    val films: List<Int>,

    @field:JsonAdapter(DeserializeIntList::class)
    val species: List<Int>,

    @field:JsonAdapter(DeserializeIntList::class)
    val starships: List<Int>,

    @field:JsonAdapter(DeserializeIntList::class)
    val vehicles: List<Int>
): Resource

/**
 * A [ResourceSet] to represent a list of [Person] resources.
 *
 * @property count the count of total resources of this type
 * @property next the numeric representation of the next paginated set of resources
 * @property previous the numeric representation of the previous paginated set of resources
 * @property people the resource data set, e.g., List<Person>
 */
data class PersonSet(
    var count: Int,

    @field:JsonAdapter(DeserializeInt::class)
    var next: Int,

    @field:JsonAdapter(DeserializeInt::class)
    var previous: Int,

    @field:SerializedName("results")
    var people: List<Person>
): ResourceSet

/**
 * A default [PersonSet] containing baseline attribute values.
 */
val DefaultPersonSet = PersonSet(
    count = 0,
    next = 1,
    previous = -1,
    people = emptyList()
)