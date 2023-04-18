package com.example.jocasta.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jocasta.data.model.Film
import com.example.jocasta.data.model.Resource
import com.example.jocasta.data.model.ResourceResponse
import com.example.jocasta.data.repository.SwapiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val swapiRepository: SwapiRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val type: String = savedStateHandle["type"] ?: "film"
    private val id: Int = savedStateHandle["id"] ?: 1

    private val _resourceState = MutableStateFlow<ResourceFetchState>(ResourceFetchState.Fetching)
    val resourceState: StateFlow<ResourceFetchState> = _resourceState

    init {
        fetchResource(type, id)
    }

    private fun fetchResource(type: String, id: Int) {
        println("fetchResource")
        when (type) {
            "film" -> fetchFilm(id)
            "person" -> fetchPerson(id)

            else -> fetchFilm(id)
        }
    }

    private fun fetchFilm(id: Int) {
        Log.i("DetailViewModel", "#fetchFilm($id)")

        viewModelScope.launch {
            when (val response = swapiRepository.fetchFilm(id)) {
                is ResourceResponse.Success -> {
                    Log.i("DetailViewModel", "#fetchFilm response .Success")
                    _resourceState.value = ResourceFetchState.Success(resource = response.resource)
                }

                is ResourceResponse.Failure -> {
                    Log.e("DetailViewModel", "#fetchFilm response .Failure")
                    _resourceState.value = ResourceFetchState.Failure
                }

                else -> {
                    Log.e("DetailViewModel", "#fetchFilm response neither .Success nor .Failure")
                    _resourceState.value = ResourceFetchState.Failure
                }
            }
        }
    }

    private fun fetchPerson(id: Int) {
        Log.i("DetailViewModel", "#fetchPerson($id)")

        viewModelScope.launch {
            when (val response = swapiRepository.fetchPerson(id)) {
                is ResourceResponse.Success -> {
                    Log.i("DetailViewModel", "#fetchPerson response .Success")
                    _resourceState.value = ResourceFetchState.Success(resource = response.resource)
                }

                is ResourceResponse.Failure -> {
                    Log.e("DetailViewModel", "#fetchPerson response .Failure")
                    _resourceState.value = ResourceFetchState.Failure
                }

                else -> {
                    Log.e("DetailViewModel", "#fetchPerson response neither .Success nor .Failure")
                    _resourceState.value = ResourceFetchState.Failure
                }
            }
        }
    }
}

sealed class ResourceFetchState {

    object Fetching : ResourceFetchState()

    data class Success(
        val resource: Resource
    ) : ResourceFetchState()

    object Failure : ResourceFetchState()
}