package `in`.iotlab.pokedeviot.data.repo

import `in`.iotlab.pokedeviot.data.model.Pokemon
import `in`.iotlab.pokedeviot.data.model.PokemonList
import `in`.iotlab.pokedeviot.data.remote.PokeApi
import `in`.iotlab.pokedeviot.data.utils.UiState
import retrofit2.Response
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokeApi) {

    suspend fun getPokemonList(limit: Int, offset: Int): UiState<PokemonList> {
        val response = api.getPokemonList(limit, offset)
        if(response.isSuccessful)
            return UiState.Success(data = response.body()!!)
        else return UiState.Error(message = "Error")
    }

    suspend fun getPokemonInfo(pokemonName: String): UiState<Pokemon> {
        val response = api.getPokemonInfo(name = pokemonName)
        if(response.isSuccessful)
            return UiState.Success(data = response.body()!!)
        else return UiState.Error(message = "Error")
    }
}