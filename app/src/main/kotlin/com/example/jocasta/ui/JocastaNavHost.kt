package com.example.jocasta.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun JocastaNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "{type}/{id}"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(
            route = "home"
        ) {
            homeScreen()
        }

        composable(
            route = "{type}/{id}",
            arguments = listOf(
                navArgument("type") { type = NavType.StringType },
                navArgument("id") { type = NavType.IntType }
            )
        ) {
            val detailViewModel: DetailViewModel = hiltViewModel()
            detailScreen(fetchState = getDetailResponseState(detailViewModel))
        }
    }
}

@Composable
fun homeScreen() {
    Text(text = "home")
}


@Composable
fun detailScreen(fetchState: ResourceFetchState) {
    val navController = rememberNavController()
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    when (fetchState) {
        is ResourceFetchState.Success -> {
            val type = savedStateHandle?.get<String>("type")
            val id = savedStateHandle?.get<Int>("id")
            Text(text = "Type: $type, ID: $id")
        }
        else -> {
            Text(text = "failure")
        }
    }
}

fun getDetailResponseState(detailViewModel: DetailViewModel): ResourceFetchState { return detailViewModel.resourceState.value }