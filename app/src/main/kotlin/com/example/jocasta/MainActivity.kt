package com.example.jocasta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jocasta.ui.JocastaNavHost
import com.example.jocasta.ui.theme.JocastaTheme
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient




@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JocastaTheme {
                JocastaNavHost()
            }
        }
    }
}