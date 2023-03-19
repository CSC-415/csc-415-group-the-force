package com.example.jocasta.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A data class to represent a single film resource.
 *
 * @property id a unique datastore film identifier
 * @property title the film title
 * @property episode the numeric saga episode
 * @property openingCrawl the opening paragraph displayed at film start
 * @property director the name of the film director
 * @property producer the name(s) of the film producer(s)
 * @property releaseDate the film release date in ISO 8601 format
 * @property species the list of species identifiers that appear in the film
 * @property starships the list of starship identifiers that appear in this film
 * @property vehicles the list of vehicle identifiers that appear in this film
 * @property characters the list of character identifiers that appear in this film
 * @property planets the list of planet identifiers that appear in this film
 * @property source the source swapi.dev hypermedia resource URL
 */
@Serializable
data class Film(
    val title: String,
    @SerialName("episode_id")
    val id: Int,
    @SerialName("opening_crawl")
    val openingCrawl: String,
    val director: String,
    val producer: String,
    @SerialName("release_date")
    val releaseDate: String,
    val characters: List<String>,
    val planets: List<String>,
    val starships: List<String>,
    val vehicles: List<String>,
    val species: List<String>,
    @SerialName("url")
    val source: String
)
