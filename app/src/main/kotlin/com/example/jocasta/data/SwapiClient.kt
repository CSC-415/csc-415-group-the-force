package com.example.jocasta.data

import com.example.jocasta.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * A request service client meant to handle interaction between SWAPI.dev and this application by
 * relaying endpoints as functions that can be called programmatically as needed.
 */
interface SwapiClient {

    /**
     * Fetch a [FilmSet] with respect to resource pagination as indicated by the query parameter.
     *
     * @param page the response page number indicating the paginated data subset that is required.
     *
     * @return a [FilmSet] containing appropriate data with respect to query parameter [page].
     */
    @GET("films/")
    suspend fun fetchFilms(
        @Query("page") page: Int
    ): Response<FilmSet>

    /**
     * Fetch a [Film] with the numeric datastore identifier indicated by the path parameter.
     *
     * @param id the numeric datastore identifier.
     *
     * @return the [Film] whose identifier field matches argument [id].
     */
    @GET("films/{id}/")
    suspend fun fetchFilm(
        @Path("id") id: Int
    ): Response<Film>

    /**
     * Fetch a [PersonSet] with respect to resource pagination as indicated by the query parameter.
     *
     * @param page the response page number indicating the paginated data subset that is required.
     *
     * @return a [PersonSet] containing appropriate data with respect to query parameter [page].
     */
    @GET("people/")
    suspend fun fetchPeople(
        @Query("page") page: Int
    ): Response<PersonSet>

    /**
     * Fetch a [Person] with the numeric datastore identifier indicated by the path parameter.
     *
     * @param id the numeric datastore identifier.
     *
     * @return the [Person] whose identifier field matches argument [id].
     */
    @GET("people/{id}/")
    suspend fun fetchPerson(
        @Path("id") id: Int
    ): Response<Person>

    @GET("planets/")
    suspend fun fetchPlanets(
        @Query("page") page: Int
    ): Response<PlanetSet>

    @GET("planets/{id}/")
    suspend fun fetchPlanet(
        @Path("id") id: Int
    ): Response<Planet>

    @GET("species/")
    suspend fun fetchAllSpecies(
        @Query("page") page: Int
    ): Response<SpeciesSet>

    @GET("species/{id}/")
    suspend fun fetchSpecies(
        @Path("id") id: Int
    ): Response<Species>
}
