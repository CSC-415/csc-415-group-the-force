package com.example.jocasta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jocasta.model.Film
import com.example.jocasta.model.Planet
import com.example.jocasta.ui.theme.JocastaTheme
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JocastaTheme {
                JocastaApplication()
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun JocastaApplication() {
    val filmList = mutableListOf<Film>()
    val planetList = mutableListOf<Planet>()
    for (i in 0..5) {
        filmList.add(
            createFilm(
                Random.nextInt(),
                "movie title: " + Random.nextInt().toString(),
                Random.nextInt(0, 7),
                "a long long time ago...",
                "George Lucas",
                "LucasFilm",
                "2/2/1999",
                "http//:somelinktoSwapi"
            )
        )
        planetList.add(
            createPlanet(
                "planet " + Random.nextInt().toString(),
                Random.nextInt(),
                Random.nextInt(),
                Random.nextInt(),
                "cold",
                "harsh",
                "solid",
                Random.nextInt(),
                Random.nextInt()
            )
        )
    }


    LazyColumn(
        modifier = Modifier.fillMaxSize().background(Color.DarkGray)
    ) {
        item {
            Spacer(modifier = Modifier.height(50.dp))
        }
        item {
            Text(text = "Films", fontSize = 25.sp, modifier = Modifier.background(Color.White, RoundedCornerShape(25)).padding(horizontal = 50.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 3),
                modifier = Modifier.height(400.dp)
            ) {
                items(filmList.size) { index ->
                    FilmCard(filmList[index])
                }
            }
        }
        item {
            Text(text = "Planets", fontSize = 25.sp, modifier = Modifier.background(Color.White, RoundedCornerShape(25)).padding(horizontal = 50.dp))
            LazyRow() {
                items(planetList) { planet ->
                    PlanetCard(planet)
                }
            }
        }
        item {
            Text(text = "Other Category...", fontSize = 25.sp, modifier = Modifier.background(Color.White, RoundedCornerShape(25)).padding(horizontal = 50.dp))
            LazyRow() {
                items(planetList) { planet ->
                    PlanetCard(planet)
                }
            }
        }
    }


}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FilmCard(film: Film) {
    GlideImage(
        model = "https://i.pinimg.com/originals/fb/bd/c0/fbbdc0b1888f90a4693b4dd4e09bc1c0.jpg",
        contentDescription = null,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp)
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlanetCard(planet: Planet) {
    GlideImage(
        model = "https://i.pinimg.com/originals/fb/bd/c0/fbbdc0b1888f90a4693b4dd4e09bc1c0.jpg",
        contentDescription = null,
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 2.dp)
            .width(125.dp)
            .height(175.dp)
    )
}

fun createFilm(
    id: Int,
    title: String,
    episode: Int,
    openingCrawl: String,
    director: String,
    producer: String,
    releaseDate: String,
    source: String
) = Film(
    id = id,
    title = title,
    episode = episode,
    openingCrawl = openingCrawl,
    director = director,
    producer = producer,
    releaseDate = releaseDate,
    source = source
)

fun createPlanet(
    name: String,
    rotationPeriod: Int,
    orbitalPeriod: Int,
    diameter: Int,
    climate: String,
    gravity: String,
    terrain: String,
    surfaceWater: Int,
    population: Int
) = Planet(
    name = name,
    rotationPeriod = rotationPeriod,
    orbitalPeriod = orbitalPeriod,
    diameter = diameter,
    climate = climate,
    gravity = gravity,
    terrain = terrain,
    surfaceWater = surfaceWater,
    population = population
)
