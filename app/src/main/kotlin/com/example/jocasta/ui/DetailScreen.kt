package com.example.jocasta.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jocasta.data.model.*

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
        is Species -> SpeciesDetailScreen(navController = navController, species = resource)
        is Planet -> PlanetDetailScreen(navController = navController, planet = resource)
        is Starship -> StarshipDetailScreen(navController = navController, starship = resource)
        is Vehicle -> VehicleDetailScreen(navController = navController, vehicle = resource)
    }
}

@Composable
fun FilmDetailScreen(
    navController: NavHostController,
    film: Film
) {
    Column(
        Modifier
            .background(Color.Black)
    ) {
        // Page Header
        Header(title = film.title, navController)

        LazyColumn(
            Modifier
                .fillMaxSize()
        ) {
            item {
                val detailArray = listOf<String>(
                    "Episode: ${film.id}",
                    "Director: ${film.director}",
                    "Producer(s): ${film.producer}",
                    "Release Data: ${film.releaseDate}"
                )
                val filmId = if (film.id < 4) film.id + 3 else film.id - 3
                TopInfo(url = "film/${filmId}", dataList = detailArray)
            }
            item { ItemRow("Characters", "person", film.people, navController) }
            item {
                ItemRow(
                    title = "Planets",
                    type = "planet",
                    idList = film.planets,
                    navController = navController
                )
            }
            item {
                ItemRow(
                    title = "Starships",
                    type = "starship",
                    idList = film.starships,
                    navController = navController
                )
            }
            item { 
                ItemRow(
                    title = "Vehicles",
                    type = "vehicle",
                    idList = film.vehicles,
                    navController = navController
                )
            }
            item {
                ItemRow(
                    title = "Species",
                    type = "species",
                    idList = film.species,
                    navController = navController
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
    Column(
        Modifier
            .background(Color.Black)
    ) {
        // Page Header
        Header(title = person.name, navController)

        LazyColumn(
            Modifier
                .fillMaxSize()
        ) {
            item {
                val detailArray = listOf<String>(
                    "Gender: ${person.gender}",
                    "Height: ${person.height}cm",
                    "Hair Color: ${person.hairColor}",
                    "Eye Color: ${person.eyeColor}",
                    "Skin Color: ${person.skinColor}",
                    "Birth Year: ${person.birthYear}"
                )
                TopInfo(url = "person/${person.id}", dataList = detailArray)
            }
            item {
                ItemRow(
                    title = "Films",
                    type = "film",
                    idList = person.films,
                    navController = navController
                )
            }
            item {
                if (person.species.isNotEmpty()) {
                    ItemRow(
                        title = "Species",
                        type = "species",
                        idList = person.species,
                        navController = navController
                    )
                }
            }
            item {
                if (person.starships.isNotEmpty()) {
                    ItemRow(
                        title = "Starships",
                        type = "starship",
                        idList = person.starships,
                        navController = navController
                    )
                }
            }
            item { 
                if (person.vehicles.isNotEmpty()) {
                    ItemRow(
                        title = "Vehicles",
                        type = "vehicle",
                        idList = person.vehicles,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun PlanetDetailScreen(
    navController: NavHostController,
    planet: Planet
) {
    Column(
        Modifier
            .background(Color.Black)
    ) {
        // Page Header
        Header(title = planet.name, navController)

        LazyColumn(
            Modifier
                .fillMaxSize()
        ) {
            item {
                val detailArray = listOf<String>(
                    "Climate: ${planet.climate}",
                    "Terrain: ${planet.terrain}",
                    "Population: ${planet.population} people",
                    "Hours in a Day: ${planet.rotationPeriod}",
                    "Days in a Year: ${planet.orbitalPeriod}",
                    "Gravity Level: ${planet.gravity}"
                )
                TopInfo(url = "planet/${planet.id}", dataList = detailArray)
            }
            item {
                ItemRow(
                    title = "Films",
                    type = "film",
                    idList = planet.films,
                    navController = navController
                )
            }
            item {
                if (planet.people.isNotEmpty()) {
                    ItemRow(
                        title = "Residents",
                        type = "person",
                        idList = planet.people,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun SpeciesDetailScreen(
    navController: NavHostController,
    species: Species
) {
    Column(
        Modifier
            .background(Color.Black)
    ) {
        // Page Header
        Header(title = species.name, navController)

        LazyColumn(
            Modifier
                .fillMaxSize()
        ) {
            item {
                val detailArray = listOf<String>(
                    "Classification: ${species.classification}",
                    "Designation: ${species.designation}",
                    "Average Height: ${species.averageHeight}",
                    "Skin Colors: ${species.skinColors}",
                    "Hair Colors: ${species.hairColors}",
                    "Eye Colors: ${species.eyeColors}",
                    "Language: ${species.language}"
                )
                TopInfo(url = "species/${species.id}", dataList = detailArray)
            }
            item {
                ItemRow(
                    title = "Films",
                    type = "film",
                    idList = species.films,
                    navController = navController
                )
            }
            item {
                ItemRow(
                    title = "People",
                    type = "person",
                    idList = species.people,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun StarshipDetailScreen(
    navController: NavHostController,
    starship: Starship
) {
    Column(
        Modifier
            .background(Color.Black)
    ) {
        // Page Header
        Header(title = starship.name, navController)

        LazyColumn(
            Modifier
                .fillMaxSize()
        ) {
            item {
                val detailArray = listOf<String>(
                    "Model: ${starship.model}",
                    "Manufacturer: ${starship.manufacturer}",
                    "Cost in Credits: ${starship.costInCredits}",
                    "Starship Class: ${starship.starshipClass}",
                    "Crew Capacity: ${starship.crew}",
                    "Passenger Capacity: ${starship.passengers}",
                    "Hyperdrive Rating: ${starship.hyperdriveRating}"
                )
                TopInfo(url = "starship/${starship.id}", dataList = detailArray)
            }
            item {
                ItemRow(
                    title = "Films",
                    type = "film",
                    idList = starship.films,
                    navController = navController
                )
            }
            item {
                if (starship.pilots.isNotEmpty()) {
                    ItemRow(
                        title = "Pilots",
                        type = "person",
                        idList = starship.pilots,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun VehicleDetailScreen(
    navController: NavHostController,
    vehicle: Vehicle
) {
    Column(
        Modifier
            .background(Color.Black)
    ) {
        // Page Header
        Header(title = vehicle.name, navController)

        LazyColumn(
            Modifier
                .fillMaxSize()
        ) {
            item {
                val detailArray = listOf<String>(
                    "Model: ${vehicle.model}",
                    "Manufacturer: ${vehicle.manufacturer}",
                    "Cost in Credits: ${vehicle.costInCredits}",
                    "Vehicle Class: ${vehicle.vehicleClass}",
                    "Crew Capacity: ${vehicle.crew}",
                    "Passenger Capacity: ${vehicle.passengers}",
                    "Speed: ${vehicle.maxAtmospheringSpeed}km/h"
                )
                TopInfo(url = "vehicle/${vehicle.id}", dataList = detailArray)
            }
            item {
                ItemRow(
                    title = "Films",
                    type = "film",
                    idList = vehicle.films,
                    navController = navController
                )
            }
            item {
                if (vehicle.pilots.isNotEmpty()) ItemRow(
                    title = "Pilots",
                    type = "person",
                    idList = vehicle.pilots,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun Header(
    title: String,
    navController: NavHostController
) {
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(4.dp)
            .clickable(onClick = {
                navController.navigate("home")
            })
        ) {
            Text(text = "HOME",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
        }
    }

    Row(Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title,
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 52.dp, end = 52.dp)
                .background(Color.White, RoundedCornerShape(15)),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold)
        )
    }

    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
            .background(Color.DarkGray)
    ) {
        Spacer(
            Modifier
                .fillMaxWidth()
                .height(12.dp))
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TopInfo(url:String, dataList: List<String>) {
    Row(
        modifier = Modifier
            .padding(8.dp)
    ) {
        GlideImage(
            model = "https://raw.githubusercontent.com/CSC-415/csc-415-group-the-force/main/data/$url.jpg",
            contentDescription = "...",
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 8.dp)
                .width(180.dp)
        ) {
            it.error(
                "https://raw.githubusercontent.com/CSC-415/csc-415-group-the-force/main/data/default.png"
            )
        }
        Column() {
            dataList.forEach {item ->
                Text(text = item,
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .background(Color.White, RoundedCornerShape(10))
                        .padding(8.dp),
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Composable
fun ItemRow(title: String, type: String, idList: List<Int>, navController: NavHostController) {
    Column(modifier = Modifier
        .height(220.dp)
        .padding(bottom = 40.dp)
    ) {
        // item header
        Text(
            text = title,
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier
                .padding(start = 16.dp)
        )
        Box(Modifier.fillMaxWidth()) {
            Spacer(
                Modifier
                    .padding(top = 50.dp)
                    .background(Color.DarkGray)
                    .fillMaxWidth()
                    .height(12.dp)
            )
            Spacer(
                Modifier
                    .padding(top = 120.dp)
                    .background(Color.DarkGray)
                    .fillMaxWidth()
                    .height(12.dp)
            )
            LazyRow(modifier = Modifier
                .fillMaxSize()) {
                items(items = idList) {
                    ResourceCard(navController = navController, type = type, id = it)
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
    }
}

