package com.example.jocasta

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jocasta.ui.HomeScreen
import com.example.jocasta.ui.ModelScreen
import com.example.jocasta.ui.theme.JocastaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // this is bad code replace with proper handling later (stack overflow suggests the AsyncTask class, but that is deprecated)
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        setContent {
            JocastaTheme {
                JocastaApplication()
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun JocastaApplication(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "home"){
        composable(route = "home"){
            HomeScreen(onClick = { navController.navigate("model") })
        }
        composable(route = "model"){
            ModelScreen()
        }
    }
}




