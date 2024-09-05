package `in`.iotlab.pokedeviot.data.remote

import `in`.iotlab.pokedeviot.data.model.Pokemon
import `in`.iotlab.pokedeviot.data.model.PokemonList
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface PokeApi {
    @GET(value = "pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit : Int,
        @Query("offset") offset: Int
    ) : Response<PokemonList>

    @GET(value = "pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String
    ) : Response<Pokemon>
}