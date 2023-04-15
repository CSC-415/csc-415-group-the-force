package com.example.jocasta.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jocasta.data.model.Film
import com.example.jocasta.data.model.Person
import com.example.jocasta.model.Planet

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ModelScreen(){
    // TODO: Added link for specified item
    Column(Modifier
        .background(Color.Black)
    ) {
        Header(nonArrayData)

        LazyColumn(Modifier
            .fillMaxSize()
        ) {
            TODO("Add ability to collect all data that is not in an array. Likey will need a function to compile into an array")
            item {
                TopInfo(nonArrayData)
            }
            TODO("Add ability to iterate through all items in swapi object that are arrays")
            items(dataList) {arrayData ->
                ItemRow(dataList = arrayData)
            }
        }
    }
}

@Composable
fun <T>Header(dataList: List<T>) {
    Row(Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "A New Hope",
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
fun TopInfo(data: String) {
    Row(Modifier
        .padding(start = 20.dp, end = 20.dp)) {
        Box(
            Modifier
                .height(200.dp)
                .width(112.dp)
                .padding(top = 16.dp, end = 8.dp)
                .background(Color.Blue)
        ) {
            FilmPoster(data.url)
        }
        Column {
            Text("Director: George Lucas",
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(Color.White, RoundedCornerShape(10))
                    .padding(8.dp)
            )
            Text("Producers: Gary Kurtz, Rick McCallum",
                Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .background(Color.White, RoundedCornerShape(10))
                    .padding(8.dp)
            )
            Text("Release: 1977-06-25",
                Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .background(Color.White, RoundedCornerShape(10))
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun <T>ItemRow(dataList: List<T>) {
    var title: String = ""
    // Will likey not work, may need to derive type from url
    when (dataList[0]) {
        is Planet -> title = "Planet"
        is Species -> title = "Species"
        is Starship -> title = "Starships"
        is Vehicle -> title = "Vehicles"
        is Film -> title = "Films"
    }
    Text(text = title, fontSize = 25.sp, modifier = Modifier
        .padding(top = 28.dp, start = 20.dp)
        .background(Color.White, RoundedCornerShape(25))
        .padding(horizontal = 50.dp)
    )
    LazyRow(Modifier
        .padding(start = 20.dp, top = 20.dp)
    ) {
        items(dataList) { data ->
            Card(data = data)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FilmPoster(data: String) {
    var location = splitURL(data)
    GlideImage(
        model = "https://raw.githubusercontent.com/CSC-415/csc-415-group-the-force/main/data/${location[0]}/${location[1]}.jpeg",
        contentDescription = null,
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 2.dp)
            .width(125.dp)
            .height(175.dp)
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
//fun CharacterCard(planet: Planet)
fun <T>Card(data: T) {
    var url = (splitURL(data.url)).takeLast(3)
    Column(
        Modifier
            .padding(end = 8.dp)
            .background(Color.White, RoundedCornerShape(5)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(Modifier
            .background(Color.White)) {
            when (data) {
                is Planet -> Text(text = data.name,
                    Modifier
                        .padding(20.dp)
                        .background(Color.Gray, RoundedCornerShape(10))
                )
                is Film -> Text(text = data.title,
                    Modifier
                        .padding(20.dp)
                        .background(Color.Gray, RoundedCornerShape(10))
                )
                is Species -> Text(text = data.name,
                    Modifier
                        .padding(20.dp)
                        .background(Color.Gray, RoundedCornerShape(10))
                )
                is Starship -> Text(text = data.s_name,
                    Modifier
                        .padding(20.dp)
                        .background(Color.Gray, RoundedCornerShape(10))
                )
                is Vehicle -> Text(text = data.v_name,
                    Modifier
                        .padding(20.dp)
                        .background(Color.Gray, RoundedCornerShape(10))
                )
            }
        }
        GlideImage(
            model = "https://starwars-visualguide.com/assets/img/${url[0]}/${url[1]}.jpg",
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 8.dp)
                .width(125.dp)
                .height(125.dp)
                .background(Color.DarkGray)
        )
    }
}

fun splitURL(url: String): List<String> {
    var objSplit = url.split("/")
    println(objSplit)
    var objLocation = objSplit.takeLast(3)
    val response = mutableListOf("", objLocation[1])
    // changing swapi to local terms
    when (objLocation[0]) {
        "films" -> response[0] = "film"
        "people" -> response[0] = "person"
        "planets" -> response[0] = "planet"
        "starships" -> response[0] = "starship"
        "vehicles" -> response[0] = "vehicle"
        "species" -> response[0] = "species"
    }
    return response
}