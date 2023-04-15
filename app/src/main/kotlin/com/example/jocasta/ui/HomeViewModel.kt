package com.example.jocasta.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jocasta.data.model.*
import com.example.jocasta.data.repository.SwapiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val swapiRepository: SwapiRepository,
) : ViewModel() {

    private val _filmSetState = MutableStateFlow<ResourceSetFetchState>(ResourceSetFetchState.Fetching)
    val filmSetState: StateFlow<ResourceSetFetchState> = _filmSetState

    private val _personSetState = MutableStateFlow<ResourceSetFetchState>(ResourceSetFetchState.Fetching)
    val personSetState: StateFlow<ResourceSetFetchState> = _personSetState

    private val _starshipSetState = MutableStateFlow<ResourceSetFetchState>(ResourceSetFetchState.Fetching)
    val starshipSetState : StateFlow<ResourceSetFetchState> = _starshipSetState

    private val _vehicleSetState = MutableStateFlow<ResourceSetFetchState>(ResourceSetFetchState.Fetching)
    val vehicleSetState : StateFlow<ResourceSetFetchState> = _vehicleSetState

    init {
        fetchFilms()
        fetchPeople()
        fetchStarships()
        fetchVehicles()
    }

    private fun fetchFilms() {
        Log.i("HomeViewModel", "#fetchFilms")

        viewModelScope.launch {
            val filmSet = DefaultFilmSet

            while (filmSet.next > 0 && _personSetState.value !is ResourceSetFetchState.Failure) {
                when (val response = swapiRepository.fetchFilms(filmSet.next)) {
                    is ResourceSetResponse.Success -> {
                        response.resourceSet as FilmSet

                        filmSet.count = response.resourceSet.count
                        filmSet.next = response.resourceSet.next
                        filmSet.previous = response.resourceSet.previous
                        filmSet.films += response.resourceSet.films
                    }

                    is ResourceSetResponse.Failure -> {
                        Log.e("HomeViewModel", "#fetchFilms response .Failure")
                        _filmSetState.value = ResourceSetFetchState.Failure

                        return@launch
                    }

                    else -> {
                        Log.w("HomeViewModel", "#fetchFilms response neither .Success nor .Failure")
                        _filmSetState.value = ResourceSetFetchState.Failure

                        return@launch
                    }
                }
            }

            Log.i("HomeViewModel", "#fetchFilms response .Success")
            _filmSetState.value = ResourceSetFetchState.Success(resourceSet = filmSet)
        }
    }

    private fun fetchPeople() {
        Log.i("HomeViewModel", "#fetchPeople")

        viewModelScope.launch {
            val personSet = DefaultPersonSet

            while (personSet.next > 0 && _personSetState.value !is ResourceSetFetchState.Failure) {
                when (val response = swapiRepository.fetchPeople(personSet.next)) {
                    is ResourceSetResponse.Success -> { response.resourceSet as PersonSet
                        personSet.count = response.resourceSet.count
                        personSet.next = response.resourceSet.next
                        personSet.previous = response.resourceSet.previous
                        personSet.people += response.resourceSet.people
                    }

                    is ResourceSetResponse.Failure -> {
                        Log.e("HomeViewModel", "#fetchPeople response .Failure")
                        _personSetState.value = ResourceSetFetchState.Failure

                        return@launch
                    }

                    else -> {
                        Log.w("HomeViewModel", "#fetchPeople response neither .Success nor .Failure")
                        _personSetState.value = ResourceSetFetchState.Failure

                        return@launch
                    }
                }
            }

            Log.i("HomeViewModel", "#fetchPeople response .Success")
            _personSetState.value = ResourceSetFetchState.Success(resourceSet = personSet)
        }
    }

    private fun fetchStarships(){
        Log.i("HomeViewModel", "#fetchStarships")
        viewModelScope.launch {
            val starshipSet = DefaultStarshipSet

            while (starshipSet.next > 0 && _starshipSetState.value !is ResourceSetFetchState.Failure) {
                when (val response = swapiRepository.fetchStarships(starshipSet.next)) {
                    is ResourceSetResponse.Success -> { response.resourceSet as StarshipSet
                        starshipSet.count = response.resourceSet.count
                        starshipSet.next = response.resourceSet.next
                        starshipSet.previous = response.resourceSet.previous
                        starshipSet.starships += response.resourceSet.starships
                    }

                    is ResourceSetResponse.Failure -> {
                        Log.e("HomeViewModel", "#fetchStarships response .Failure")
                        _starshipSetState.value = ResourceSetFetchState.Failure

                        return@launch
                    }

                    else -> {
                        Log.w("HomeViewModel", "#fetchStarships response neither .Success nor .Failure")
                        _starshipSetState.value = ResourceSetFetchState.Failure

                        return@launch
                    }
                }
            }

            Log.i("HomeViewModel", "#fetchStarships response .Success")
            _personSetState.value = ResourceSetFetchState.Success(resourceSet = starshipSet)
        }

    }
    private fun fetchVehicles(){
        val vehicleSet = DefaultVehicleSet

        viewModelScope.launch {
            while (vehicleSet.next > 0 && _vehicleSetState.value !is ResourceSetFetchState.Failure) {
                when (val response = swapiRepository.fetchStarships(vehicleSet.next)) {
                    is ResourceSetResponse.Success -> { response.resourceSet as VehicleSet
                        vehicleSet.count = response.resourceSet.count
                        vehicleSet.next = response.resourceSet.next
                        vehicleSet.previous = response.resourceSet.previous
                        vehicleSet.vehicles += response.resourceSet.vehicles
                    }

                    is ResourceSetResponse.Failure -> {
                        Log.e("HomeViewModel", "#fetchVehicles response .Failure")
                        _vehicleSetState.value = ResourceSetFetchState.Failure

                        return@launch
                    }

                    else -> {
                        Log.w("HomeViewModel", "#fetchVehicles response neither .Success nor .Failure")
                        _vehicleSetState.value = ResourceSetFetchState.Failure

                        return@launch
                    }
                }
            }

            Log.i("HomeViewModel", "#fetchVehicles response .Success")
            _personSetState.value = ResourceSetFetchState.Success(resourceSet = vehicleSet)
        }
    }

}


/**
 * A sealed class container consisting of all [ResourceSet] fetch states.
 */
sealed class ResourceSetFetchState {

    /**
     * A [ResourceSetFetchState] indicating an in-progress fetch awaiting response fulfillment.
     */
    object Fetching : ResourceSetFetchState()

    /**
     * A [ResourceSetFetchState] indicating a successful fetch and response fulfillment.
     *
     * @property resourceSet a resulting [ResourceSet].
     */
    data class Success(
        val resourceSet: ResourceSet
    ) : ResourceSetFetchState()

    /**
     * A [ResourceSetFetchState] indicating a failed fetch and a lack of appropriate response.
     */
    object Failure : ResourceSetFetchState()
}