package com.example.jocasta.data.repository

import android.util.Log
import com.example.jocasta.data.SwapiClient
import com.example.jocasta.data.model.PersonSet
import com.example.jocasta.data.model.ResourceSetResponse
import javax.inject.Inject

class SwapiRepositoryImpl @Inject constructor(
    private val client: SwapiClient
) : SwapiRepository {

    override suspend fun fetchPeople(page: Int): ResourceSetResponse {
        Log.i("SwapiRepositoryImpl", "#fetchPeople")

        val response = client.fetchPeople(page)

        if (!response.isSuccessful) {
            return ResourceSetResponse.Failure
        }

        val body = response.body()

        return if (body !== null) {
            ResourceSetResponse.Success(
                PersonSet(
                    count = body.count,
                    next = body.next,
                    previous = body.previous,
                    people = body.people
                )
            )
        } else {
            ResourceSetResponse.Failure
        }
    }
}