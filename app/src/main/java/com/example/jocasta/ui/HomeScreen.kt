package com.example.jocasta.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jocasta.model.Film
import com.example.jocasta.model.Planet


@Composable
fun HomeScreen(
    onClick: () -> Unit
) {
    val dataViewModel: DataViewModel = DataViewModel()
    val filmList = dataViewModel.filmList
    val planetList = dataViewModel.planetList.subList(0, 6)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        item {
            Spacer(modifier = Modifier.height(50.dp))
        }
        item {
            DataGrid("Films", dataList = filmList, onClick = onClick)
        }
        item {
            DataRow(text = "Planets", dataList = planetList)
        }
        item {
            DataRow(text = "Other Categories...", dataList = planetList)
        }
    }

}


@Composable
fun <T>DataGrid(text: String, dataList: List<T>, onClick: () -> Unit){
    Text(text = text, fontSize = 25.sp, modifier = Modifier
        .background(Color.White, RoundedCornerShape(25))
        .padding(horizontal = 50.dp))
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 3),
        //modifier = Modifier.height(400.dp) height for without title film text
        modifier = Modifier.height(500.dp)
    ) {
        items(dataList.size) { index ->
            val data = dataList[index]
            if (data is Film)
                FilmCard(data, onClick = onClick)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FilmCard(film: Film, onClick: () -> Unit) {
    Column() {
        Text(text = film.id.toString() + ": " +  film.title, color = Color.White)
        GlideImage(
            model = "https://i.pinimg.com/originals/fb/bd/c0/fbbdc0b1888f90a4693b4dd4e09bc1c0.jpg",
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 4.dp)
                .clickable(onClick = onClick)
        )
    }
}

@Composable
fun <T>DataRow(text: String, dataList: List<T>){
    Text(text = text, fontSize = 25.sp, modifier = Modifier
        .background(Color.White, RoundedCornerShape(25))
        .padding(horizontal = 50.dp))
    LazyRow() {
        items(dataList) { data ->
            if (data is Planet)
                PlanetCard(planet = data)
            //TODO write the conditions and cards for the rest of the models
            // if(data is Character)...
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlanetCard(planet: Planet) {
    Column() {
        Text(text = planet.name, color = Color.White)
        GlideImage(
            model = "https://i.pinimg.com/originals/fb/bd/c0/fbbdc0b1888f90a4693b4dd4e09bc1c0.jpg",
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 2.dp)
                .width(125.dp)
                .height(175.dp)
        )
    }
}