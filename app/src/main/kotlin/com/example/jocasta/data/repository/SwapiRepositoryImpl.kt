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

    override suspend fun fetchStarships(page: Int): ResourceSetResponse {
        Log.i("SwapiRepositoryImpl", "#fetchFilms")

        val response = client.fetchStarship(page)

        if (!response.isSuccessful) {
            return ResourceSetResponse.Failure
        }

        val body = response.body()

        return if (body !== null) {
            ResourceSetResponse.Success(
                StarshipSet(
                    count = body.count,
                    next = body.next,
                    previous = body.previous,
                    starships = body.starships
                )
            )
        } else {
            ResourceSetResponse.Failure
        }
    }

    override suspend fun fetchVehicles(page: Int): ResourceSetResponse {
        Log.i("SwapiRepositoryImpl", "#fetchVehicle")

        val response = client.fetchVehicle(page)

        if(!response.isSuccessful){
            return ResourceSetResponse.Failure
        }

        val body = response.body()

        return if (body !== null){
            ResourceSetResponse.Success(
                VehicleSet(
                    count = body.count,
                    next = body.next,
                    previous = body.previous,
                    vehicles = body.vehicles
                )
            )
        }else{
            ResourceSetResponse.Failure
        }
    }
}