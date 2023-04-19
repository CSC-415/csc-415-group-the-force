package com.example.jocasta.ui

import android.util.Log
import androidx.compose.foundation.background
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
import com.example.jocasta.data.model.Film
import com.example.jocasta.data.model.Person
import com.example.jocasta.data.model.Resource
import com.example.jocasta.data.model.Species

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
    }
}

@Composable
fun FilmDetailScreen(
    navController: NavHostController,
    film: Film
) {
    LazyColumn (
        Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        item {
            // Page Header
            header(title = film.title)
        }
        item {
            var detailArray = listOf<String>(
                "Episode: ${film.episode}", "Director: ${film.director}", "Producer(s): ${film.producer}", "Release Data: ${film.releaseDate}"
            )
            topInfo(url = "film/${film.id}", dataList = detailArray)
        }
        item { itemRow("Characters", "person", film.people, navController) }
        item { itemRow(title = "Planets", type = "planet", idList = film.planets, navController = navController) }
        item { itemRow(title = "Species", type = "species", idList = film.species, navController = navController) }
    }
}

@Composable
fun PersonDetailScreen(
    navController: NavHostController,
    person: Person
) {
    LazyColumn (
        Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        item {
            // Page Header
            header(title = person.name)
        }
        item {
            var detailArray = listOf<String>(
                "Gender: ${person.gender}", "Height: ${person.height}", "Hair Color: ${person.hairColor}",
                "Eye Color: ${person.eyeColor}", "Skin Color: ${person.skinColor}", "Birth Year: ${person.birthYear}"
            )
            topInfo(url = "person/${person.id}", dataList = detailArray)
        }
        item { itemRow(title = "Films", type = "film", idList = person.films, navController = navController) }
    }
}

@Composable
fun SpeciesDetailScreen(
    navController: NavHostController,
    species: Species
) {
    LazyColumn (
        Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        item {
            // Page Header
            header(title = species.name)
        }
        item {
            var detailArray = listOf<String>(
                "Classification: ${species.classification}", "Designation: ${species.designation}", "Average Height: ${species.averageHeight}",
                "Skin Colors: ${species.skinColors}", "Hair Colors: ${species.hairColors}", "Eye Colors: ${species.eyeColors}",
                "Language: ${species.language}"
            )
            topInfo(url = "species/${species.id}", dataList = detailArray)
        }
        item { itemRow(title = "Films", type = "film", idList = species.films, navController = navController) }
        item { itemRow(title = "People", type = "person", idList = species.people, navController = navController) }
    }
}

@Composable
fun header(title: String) {
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
fun topInfo(url:String, dataList: List<String>) {
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
        )
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
fun itemRow(title: String, type: String, idList: List<Int>, navController: NavHostController) {
    Column(modifier = Modifier
        .height(200.dp)
    ) {
        // item header
        Text(
            text = title,
            style = TextStyle(
                fontSize = 20.sp,
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

