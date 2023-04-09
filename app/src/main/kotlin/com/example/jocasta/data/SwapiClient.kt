package com.example.jocasta.data

import com.example.jocasta.data.model.PersonSet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * A request service client meant to handle interaction between SWAPI.dev and this application by
 * relaying endpoints as functions that can be called programmatically as needed.
 */
interface SwapiClient {

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
}