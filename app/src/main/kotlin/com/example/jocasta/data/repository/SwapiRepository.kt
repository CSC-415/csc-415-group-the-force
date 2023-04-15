package com.example.jocasta.data.repository

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

<<<<<<< HEAD
    /**
     * Fetch a [Person] with the numeric datastore identifier indicated by the path parameter.
     *
     * @param id the numeric datastore identifier.
     *
     * @return the [Person] whose identifier field matches argument [id].
     */
    suspend fun fetchPerson(
        id: Int
    ): ResourceResponse
=======
    suspend fun fetchPlanets(
        page: Int
    ): ResourceSetResponse

    suspend fun fetchPlanet(
        id: Int
    ): ResourceSetResponse

    suspend fun fetchAllSpecies(
        page: Int
    ): ResourceSetResponse
<<<<<<< HEAD
>>>>>>> 87b17af (Added the code for the planet and species data models to fit the new data model schema)
||||||| parent of f4818db (fixed code issues)
=======

    suspend fun fetchSpecies(
        id: Int
    ): ResourceSetResponse
>>>>>>> f4818db (fixed code issues)
}