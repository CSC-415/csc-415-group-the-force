package com.example.jocasta.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jocasta.data.model.Film
import com.example.jocasta.data.model.Person
import com.example.jocasta.data.model.Resource

@Composable
fun DetailRoute(
    navController: NavHostController,
    viewModel: DetailViewModel = hiltViewModel()
) {
    when (val response = viewModel.resourceState.collectAsState().value) {
        is ResourceFetchState.Success -> {
            DetailScreen(
                navController = navController,
                resource = response.resource
            )
        }

        is ResourceFetchState.Fetching -> {
            Text(
                text = "Loading..."
            )
        }

        else -> Text(text = "Failure Loading")
    }
}

@Composable
fun DetailScreen(
    navController: NavHostController,
    resource: Resource
) {
    when (resource) {
        is Film -> FilmDetailScreen(navController = navController, film = resource)
        is Person -> PersonDetailScreen(navController = navController, person = resource)
    }
}

@Composable
fun FilmDetailScreen(
    navController: NavHostController,
    film: Film
) {
    Column {
        Text(
            text = "Star Wars: ${film.title}"
        )

        LazyRow {
            items(
                items = film.people
            ) {
                ResourceCard(
                    navController = navController,
                    type = "person",
                    id = it
                )
            }
        }
    }
}

@Composable
fun PersonDetailScreen(
    navController: NavHostController,
    person: Person
) {
    Column {
        Text(
            text = person.name
        )

        LazyRow {
            items(
                items = person.films
            ) {
                ResourceCard(
                    navController = navController,
                    type = "film",
                    id = it
                )
            }
        }
    }
}