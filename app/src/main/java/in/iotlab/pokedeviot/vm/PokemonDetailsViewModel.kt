package `in`.iotlab.pokedeviot.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.iotlab.pokedeviot.data.model.Pokemon
import `in`.iotlab.pokedeviot.data.repo.PokemonRepository
import `in`.iotlab.pokedeviot.data.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val repository: PokemonRepository) : ViewModel() {

    private val _pokemonDetails: MutableStateFlow<UiState<Pokemon>> = MutableStateFlow(UiState.Idle)
    val pokemonDetails = _pokemonDetails.asStateFlow()

    fun pokemonDetails(pokemonName: String) {
        getPokemonDetails(pokemonName = pokemonName)
    }

    private fun getPokemonDetails(pokemonName: String) {
        _pokemonDetails.value = UiState.Loading

        viewModelScope.launch {
            try {
                _pokemonDetails.value = repository.getPokemonInfo(pokemonName = pokemonName)
            }
            catch (e: Exception) {
                Log.d("PokemonDetailsViewModel", "getAnimeInfo: ${e.message}")
            }
        }
    }
}