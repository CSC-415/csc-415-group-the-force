package com.example.jocasta.data.model

import com.example.jocasta.utility.DeserializeInt
import com.example.jocasta.utility.DeserializeIntList
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

/**
 * A [Resource] to represent a film within the cinematic universe.
 *
 * @property id the unique datastore film identifier, extracted from the episodic order
 * @property title the film title, which is more accurately the subtitle (e.g., A New Hope)
 * @property openingCrawl the opening paragraph displayed at film start
 * @property director the name(s) of the film director(s)
 * @property producer the name(s) of the film producer(s)
 * @property releaseDate the film release date in ISO 8601 format
 * @property people the list of [Person] identifiers that appear in this film
 * @property planets the list of planet identifiers that appear in this film
 * @property species the list of species identifiers that appear in this film
 * @property starships the list of starship identifiers that appear in this film
 * @property vehicles the list of vehicle identifiers that appear in this film
 */
data class Film(
    @field:SerializedName("url")
    @field:JsonAdapter(DeserializeInt::class)
    val id: Int,

    val title: String,

    @field:SerializedName("episode_id")
    val episode: Int,

    @field:SerializedName("opening_crawl")
    val openingCrawl: String,

    val director: String,
    val producer: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("characters")
    @field:JsonAdapter(DeserializeIntList::class)
    val people: List<Int>,

    @field:JsonAdapter(DeserializeIntList::class)
    val planets: List<Int>,

    @field:JsonAdapter(DeserializeIntList::class)
    val species: List<Int>,

    @field:JsonAdapter(DeserializeIntList::class)
    val starships: List<Int>,

    @field:JsonAdapter(DeserializeIntList::class)
    val vehicles: List<Int>,
): Resource

/**
 * A [ResourceSet] to represent a list of [Film] resources.
 *
 * @property count the count of total resources of this type
 * @property next the numeric representation of the next paginated set of resources
 * @property previous the numeric representation of the previous paginated set of resources
 * @property films the resource data set, e.g., [List]<[Film]>
 */
data class FilmSet(
    var count: Int,

    @field:JsonAdapter(DeserializeInt::class)
    var next: Int,

    @field:JsonAdapter(DeserializeInt::class)
    var previous: Int,

    @field:SerializedName("results")
    var films: List<Film>
): ResourceSet

/**
 * A default [FilmSet] containing baseline attribute values.
 */
val DefaultFilmSet = FilmSet(
    count = 0,
    next = 1,
    previous = -1,
    films = emptyList()
)