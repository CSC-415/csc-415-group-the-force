package com.example.jocasta.data.repository

import android.util.Log
import com.example.jocasta.data.SwapiClient
import com.example.jocasta.data.model.*
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

<<<<<<< HEAD
    override suspend fun fetchPerson(id: Int): ResourceResponse {
        Log.i("SwapiRepositoryImpl", "#fetchPerson($id)")

        val response = client.fetchPerson(id)

        if (!response.isSuccessful) {
            return ResourceResponse.Failure
=======
    override suspend fun fetchPlanets(page: Int): ResourceSetResponse {
        Log.i("SwapiRepositoryImpl", "#fetchPlanets")

        val response = client.fetchPlanets(page)

        if (!response.isSuccessful) {
            return ResourceSetResponse.Failure
>>>>>>> 87b17af (Added the code for the planet and species data models to fit the new data model schema)
        }

        val body = response.body()

        return if (body !== null) {
<<<<<<< HEAD
            ResourceResponse.Success(body)
        } else {
            ResourceResponse.Failure
=======
            ResourceSetResponse.Success(
                PlanetSet(
                    count = body.count,
                    next = body.next,
                    previous = body.previous,
                    planets = body.planets
                )
            )
        } else {
            ResourceSetResponse.Failure
        }
    }

    override suspend fun fetchSpecies(page: Int): ResourceSetResponse {
        Log.i("SwapiRepositoryImpl", "#fetchSpecies")

        val response = client.fetchSpecies(page)

        if (!response.isSuccessful) {
            return ResourceSetResponse.Failure
        }

        val body = response.body()

        return if (body !== null) {
            ResourceSetResponse.Success(
                SpeciesSet(
                    count = body.count,
                    next = body.next,
                    previous = body.previous,
                    species = body.species
                )
            )
        } else {
            ResourceSetResponse.Failure
>>>>>>> 87b17af (Added the code for the planet and species data models to fit the new data model schema)
        }
    }
}