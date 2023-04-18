package com.example.jocasta.data

import com.example.jocasta.data.model.FilmSet
import com.example.jocasta.data.model.PersonSet
import com.example.jocasta.data.model.StarshipSet
import com.example.jocasta.data.model.VehicleSet
import retrofit2.Response
import retrofit2.http.GET
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

    @GET("starships/")
    suspend fun fetchStarship(
        @Query("page") page: Int
    ): Response<StarshipSet>

    @GET("vehicles/")
    suspend fun fetchVehicle(
        @Query("page") page: Int
    ): Response<VehicleSet>
}