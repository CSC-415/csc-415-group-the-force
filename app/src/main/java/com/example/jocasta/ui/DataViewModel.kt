package com.example.jocasta.ui

import androidx.lifecycle.ViewModel
import com.example.jocasta.model.Film
import com.example.jocasta.model.Planet
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

@kotlinx.serialization.Serializable
data class Wrapper<T>(
    val results: List<T>
)

class DataViewModel: ViewModel (){

    private val client = OkHttpClient()
    private val json = Json { ignoreUnknownKeys = true }
    val filmList: List<Film> = json.decodeFromString<Wrapper<Film>>(run("https://swapi.dev/api/films")).results
    val planetList: List<Planet> = json.decodeFromString<Wrapper<Planet>>(run("https://swapi.dev/api/planets/")).results

    private fun run(url: String) : String{
        var resultHeader: String = ""
        var result: String = ""
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            for ((name, value) in response.headers) {
                resultHeader += "$name: $value"
            }

            result += response.body!!.string()
        }
        return result
}


}