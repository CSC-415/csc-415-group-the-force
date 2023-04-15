package com.example.jocasta.data.repository

import android.util.Log
import com.example.jocasta.data.SwapiClient
import com.example.jocasta.data.model.ResourceResponse
import com.example.jocasta.data.model.ResourceSetResponse
import javax.inject.Inject

class SwapiRepositoryImpl @Inject constructor(
    private val client: SwapiClient
) : SwapiRepository {

    override suspend fun fetchFilms(page: Int): ResourceSetResponse {
        Log.i("SwapiRepositoryImpl", "#fetchFilms")

        val response = client.fetchFilms(page)

        if (!response.isSuccessful) {
            return ResourceSetResponse.Failure
        }

        val body = response.body()

        return if (body !== null) {
            ResourceSetResponse.Success(body)
        } else {
            ResourceSetResponse.Failure
        }
    }

    override suspend fun fetchFilm(id: Int): ResourceResponse {
        Log.i("SwapiRepositoryImpl", "#fetchFilm($id)")

        val response = client.fetchFilm(id)

        if (!response.isSuccessful) {
            return ResourceResponse.Failure
        }

        val body = response.body()

        return if (body !== null) {
            ResourceResponse.Success(body)
        } else {
            ResourceResponse.Failure
        }
    }

    override suspend fun fetchPeople(page: Int): ResourceSetResponse {
        Log.i("SwapiRepositoryImpl", "#fetchPeople")

        val response = client.fetchPeople(page)

        if (!response.isSuccessful) {
            return ResourceSetResponse.Failure
        }

        val body = response.body()

        return if (body !== null) {
            ResourceSetResponse.Success(body)
        } else {
            ResourceSetResponse.Failure
        }
    }

    override suspend fun fetchPerson(id: Int): ResourceResponse {
        Log.i("SwapiRepositoryImpl", "#fetchPerson($id)")

        val response = client.fetchPerson(id)

        if (!response.isSuccessful) {
            return ResourceResponse.Failure
        }

        val body = response.body()

        return if (body !== null) {
            ResourceResponse.Success(body)
        } else {
            ResourceResponse.Failure
        }
    }

    override suspend fun fetchPlanets(page: Int): ResourceSetResponse {
        Log.i("SwapiRepositoryImpl", "#fetchPlanets")

        val response = client.fetchPlanets(page)

        if (!response.isSuccessful) {
            return ResourceSetResponse.Failure
        }

        val body = response.body()

        return if (body !== null) {
            ResourceSetResponse.Success(body)
        } else {
            ResourceSetResponse.Failure
        }
    }

    override suspend fun fetchPlanet(id: Int): ResourceResponse {
        Log.i("SwapiRepositoryImpl", "#fetchPlanet($id)")

        val response = client.fetchPlanet(id)

        if (!response.isSuccessful) {
            return ResourceResponse.Failure
        }

        val body = response.body()

        return if (body !== null) {
            ResourceResponse.Success(body)
        } else {
            ResourceResponse.Failure
        }
    }

    override suspend fun fetchAllSpecies(page: Int): ResourceSetResponse {
        Log.i("SwapiRepositoryImpl", "#fetchAllSpecies")

        val response = client.fetchAllSpecies(page)

        if (!response.isSuccessful) {
            return ResourceSetResponse.Failure
        }

        val body = response.body()

        return if (body !== null) {
            ResourceSetResponse.Success(body)
        } else {
            ResourceSetResponse.Failure
        }
    }

    override suspend fun fetchSpecies(id: Int): ResourceResponse {
        Log.i("SwapiRepositoryImpl", "#fetchSpecies($id)")

        val response = client.fetchSpecies(id)

        if (!response.isSuccessful) {
            return ResourceResponse.Failure
        }

        val body = response.body()

        return if (body !== null) {
            ResourceResponse.Success(body)
        } else {
            ResourceResponse.Failure
        }
    }
}

