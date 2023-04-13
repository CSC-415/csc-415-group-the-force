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
            ResourceSetResponse.Success(
                FilmSet(
                    count = body.count,
                    next = body.next,
                    previous = body.previous,
                    films = body.films
                )
            )
        } else {
            ResourceSetResponse.Failure
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

    override suspend fun fetchPlanets(page: Int): ResourceSetResponse {
        Log.i("SwapiRepositoryImpl", "#fetchPlanets")

        val response = client.fetchPlanets(page)

        if (!response.isSuccessful) {
            return ResourceSetResponse.Failure
        }

        val body = response.body()

        return if (body !== null) {
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
        }
    }
}