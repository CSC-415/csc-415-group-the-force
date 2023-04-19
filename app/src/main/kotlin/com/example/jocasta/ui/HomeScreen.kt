package com.example.jocasta.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jocasta.data.model.FilmSet
import com.example.jocasta.data.model.PersonSet
import com.example.jocasta.data.model.PlanetSet
import com.example.jocasta.data.model.Resource

@Composable
fun HomeRoute(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    when (val filmSetResponse = viewModel.filmSetState.collectAsState().value) {
        is ResourceSetFetchState.Success -> {
            HomeScreen(navController = navController, filmSet = filmSetResponse.resourceSet as FilmSet)
        }

        is ResourceSetFetchState.Fetching -> {
            Text(text = "Loading...")
        }

        else -> Text(text = "Failure Loading")
    }
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,
    filmSet: FilmSet,
    //planetSet: PlanetSet
) {
    val personSetResponse = viewModel.personSetState.collectAsState().value
    val planetSetResponse = viewModel.planetSetState.collectAsState().value

    LazyColumn {
        item {
            ContentHeader(
                text = "Films"
            )
        }

        item {
            ContentRow(
                navController = navController,
                resources = filmSet.films
            )
        }

        when (personSetResponse) {
            is ResourceSetFetchState.Success -> {
                item {
                    ContentHeader(
                        text = "People"
                    )
                }

                item {
                    ContentRow(
                        navController = navController,
                        resources = (personSetResponse.resourceSet as PersonSet).people
                    )
                }
            }

            else -> { /* swallow - show nothing */ }
        }

//        item {
//            ContentRow(
//                navController = navController,
//                resources = planetSet.planets
//            )
//        }

        when (planetSetResponse) {
            is ResourceSetFetchState.Success -> {
                item {
                    ContentHeader(
                        text = "Planet"
                    )
                }

                item {
                    ContentRow(
                        navController = navController,
                        resources = (planetSetResponse.resourceSet as PlanetSet).planets
                    )
                }
            }

            else -> { /* swallow - show nothing */ }
        }
    }
}

@Composable
fun ContentHeader(
    text: String
) {
    Text(
        text = text,
        fontWeight = FontWeight(FontWeight.Bold.weight),
        fontSize = 32.sp
    )
}

@Composable
fun ContentRow(
    navController: NavHostController,
    resources: List<Resource>
) {
    LazyRow {
        items(
            items = resources
        ) {resource ->
            ContentColumn(
                navController = navController,
                resource = resource
            )
        }
    }
}

@Composable
fun ContentColumn(
    navController: NavHostController,
    resource: Resource,
    modifier: Modifier = Modifier
        .height(100.dp)
        .width(100.dp)
) {
    Column(modifier = modifier) {
        ResourceCard(
            navController = navController,
            resource = resource
        )
    }
}
