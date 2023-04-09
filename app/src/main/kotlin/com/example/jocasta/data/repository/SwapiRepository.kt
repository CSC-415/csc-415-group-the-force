package com.example.jocasta.data.repository

import com.example.jocasta.data.model.PersonSet
import com.example.jocasta.data.model.ResourceSetResponse

/**
 * A resource repository interface meant to structure the application-side middleware service layer
 *  which determines how client requests should be made and how request state should change as a
 *  result.
 */
interface SwapiRepository {

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
}