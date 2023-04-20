package com.example.jocasta.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jocasta.data.model.*

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ResourceCard(
    navController: NavHostController,
    resource: Resource
) {

    val path = when (resource) {
        is Film -> "film/${resource.id}"
        is Person -> "person/${resource.id}"
        is Planet -> "planet/${resource.id}"
        is Species -> "species/${resource.id}"
        is Starship -> "starhip/${resource.id}"

        else -> "film/1"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                navController.navigate(path)
            }),
        shape = RoundedCornerShape(16.dp)
    ) {
        GlideImage(
            model = "https://raw.githubusercontent.com/CSC-415/csc-415-group-the-force/main/data/$path.jpg",
            contentDescription = "..."
        ) {
            it.error(
                "https://raw.githubusercontent.com/CSC-415/csc-415-group-the-force/main/data/default.png"
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ResourceCard(
    navController: NavHostController,
    type: String,
    id: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                navController.navigate("$type/$id")
            }),
        shape = RoundedCornerShape(16.dp)
    ) {
        GlideImage(
            model = "https://raw.githubusercontent.com/CSC-415/csc-415-group-the-force/main/data/$type/$id.jpg",
            contentDescription = "..."
        ) {
            it.error(
                "https://raw.githubusercontent.com/CSC-415/csc-415-group-the-force/main/data/default.png"
            )
        }
    }
}