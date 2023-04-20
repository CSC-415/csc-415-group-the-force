package com.example.jocasta.data.repository


import com.example.jocasta.data.model.FilmSet
import com.example.jocasta.data.model.PersonSet
import com.example.jocasta.data.model.ResourceResponse
import com.example.jocasta.data.model.ResourceSetResponse

import com.example.jocasta.data.model.*


/**
 * A resource repository interface meant to structure the application-side middleware service layer
 *  which determines how client requests should be made and how request state should change as a
 *  result.
 */
interface SwapiRepository {

    /**
     * Fetch a [FilmSet] with respect to resource pagination as indicated by the query parameter.
     *
     * @param page the response page number indicating the paginated data subset that is required.
     *
     * @return a [FilmSet] containing appropriate data with respect to query parameter [page].
     */
    suspend fun fetchFilms(
        page: Int
    ): ResourceSetResponse
    /**
     * Fetch a [Film] with the numeric datastore identifier indicated by the path parameter.
     *
     * @param id the numeric datastore identifier.
     *
     * @return the [Film] whose identifier field matches argument [id].
     */
    /**
     * Fetch a [Film] with the numeric datastore identifier indicated by the path parameter.
     *
     * @param id the numeric datastore identifier.
     *
     * @return the [Film] whose identifier field matches argument [id].
     */
    suspend fun fetchFilm(
        id: Int
    ): ResourceResponse

    /**
     * Fetch a [PersonSet] with respect to resource pagination as indicated by the query parameter.
     *
     * @param page the response page number indicating the paginated data subset that is required.
     *
     * @return a [PersonSet] containing appropriate data with respect to query parameter [page].
     */
    suspend fun fetchPeople(
        page: Int
    ): ResourceSetResponse


    suspend fun fetchPerson(
        id: Int
    ): ResourceResponse

    suspend fun fetchPlanets(
        page: Int
    ): ResourceSetResponse

    suspend fun fetchPlanet(
        id: Int
    ): ResourceResponse

    suspend fun fetchAllSpecies(
        page: Int
    ): ResourceSetResponse

    suspend fun fetchSpecies(
        id: Int
    ): ResourceResponse
    suspend fun fetchStarships(
        page: Int
    ):ResourceSetResponse
    suspend fun fetchStarship(
        id: Int
    ):ResourceResponse

    suspend fun fetchVehicles(
        page: Int
    ):ResourceSetResponse

    suspend fun fetchVehicle(
        id: Int
    ):ResourceResponse
}

/**
 * Fetch a [Person] with the numeric datastore identifier indicated by the path parameter.
 *
 * @param id the numeric datastore identifier.
 *
 * @return the [Person] whose identifier field matches argument [id].
 */