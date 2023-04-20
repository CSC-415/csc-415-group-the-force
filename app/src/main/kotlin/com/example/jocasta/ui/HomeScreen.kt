package com.example.jocasta.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jocasta.data.model.*

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
    val gradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF000000),
            Color(0xFF000000),
            Color(0xFF4B0082),
            Color(0xFF000000),
            Color(0xFFDDA0DD),
            Color(0xFF000000),
            Color(0xFF4B0082),
            Color(0xFF000000),
            Color(0xFF000000)
        ),
        start = Offset(0f, 0f),
        end = Offset(0f, Float.POSITIVE_INFINITY)
    )

    Column(
        modifier = Modifier
            .background(color = Color(0xFF1D1D1D))
    ){
        Box(modifier = Modifier
            .padding(end = 58.dp)
            .padding(bottom = 10.dp))
        {
            Text(text = "     ‿︵‿  Jacosta  ‿︵‿",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                modifier = Modifier,
                color = Color(0xFFF5C542)
            )
        }
    }


    val personSetResponse = viewModel.personSetState.collectAsState().value
    val planetSetResponse = viewModel.planetSetState.collectAsState().value
    val speciesSetResponse = viewModel.speciesSetState.collectAsState().value
    val starShipResponse = viewModel.starshipSetState.collectAsState().value
    val vehicleResponse = viewModel.vehicleSetState.collectAsState().value


    LazyColumn(
        modifier = Modifier
            .padding(top = 50.dp)
            .background(brush = gradient)
    ) {
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

        when (speciesSetResponse) {
            is ResourceSetFetchState.Success -> {
                item {
                    ContentHeader(
                        text = "Species"
                    )
                }

                item {
                    ContentRow(
                        navController = navController,
                        resources = (speciesSetResponse.resourceSet as SpeciesSet).species
                    )
                }
            }

            else -> { /* swallow - show nothing */ }
        }
        when (starShipResponse) {
            is ResourceSetFetchState.Success -> {
                item {
                    ContentHeader(
                        text = "Starship"
                    )
                }

                item {
                    ContentRow(
                        navController = navController,
                        resources = (starShipResponse.resourceSet as StarshipSet).starships
                    )
                }
            }

            else -> { /* swallow - show nothing */ }
        }
        when (vehicleResponse) {
            is ResourceSetFetchState.Success -> {
                item {
                    ContentHeader(
                        text = "Vehicle"
                    )
                }

                item {
                    ContentRow(
                        navController = navController,
                        resources = (vehicleResponse.resourceSet as VehicleSet).vehicles
                    )
                }
            }

            else -> { /* swallow - show nothing */ }
        }
    }
}

@Composable
fun ContentHeader(
    text: String,
) {
    Box(
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp)
            .background(color = Color(0xFF3E3E3E))
    ){
        Text(modifier = Modifier
            .padding(end = 100.dp),
            text = text,
            fontWeight = FontWeight(FontWeight.Bold.weight),
            fontSize = 20.sp,
            color = Color(0xFFF5C542)
        )
    }
}

@Composable
fun ContentRow(
    navController: NavHostController,
    resources: List<Resource>,
    modifier: Modifier = Modifier
        .background(Color(0xFF2A2A2A))
        .padding(top = 5.dp, bottom = 5.dp)
) {
    LazyRow(modifier = modifier) {
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
        .padding(top = 10.dp, bottom = 10.dp)
) {
    Column(modifier = modifier) {
        ResourceCard(
            navController = navController,
            resource = resource
        )
    }
}
