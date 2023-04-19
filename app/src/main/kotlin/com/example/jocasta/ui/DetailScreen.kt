package com.example.jocasta.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jocasta.data.model.Film
import com.example.jocasta.data.model.Person
import com.example.jocasta.data.model.Planet
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
    LazyColumn (
        Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        item {
            // Page Header
            header(title = film.title)
        }
        //        topInfo(dataList = )
        item { itemRow("Characters", "person", film.people, navController) }
        item { itemRow(title = "Planets", type = "planet", idList = film.planets, navController = navController) }
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

@Composable
fun topInfo(dataList: List<String>) {

}

@Composable
fun itemRow(title: String, type: String, idList: List<Int>, navController: NavHostController) {
    Column {
        // item header
        Text(
            text = title,
            color = Color.White
        )
        LazyRow(
            Modifier
                .fillMaxSize(),
        ) {
            items(
                items = idList
            ) {
                ResourceCard(
                    navController = navController,
                    type = type,
                    id = it
                )
            }
        }
    }
}