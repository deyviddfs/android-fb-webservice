package br.com.deyvidfernandes.feedbackwebservice.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val URL = "https://api.pokemontcg.io/v2/"

private val moshi = Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()

private val retrofit = Retrofit.Builder()
                        .addConverterFactory(MoshiConverterFactory.create(moshi))
                        .baseUrl(URL)
                        .build()

interface PokemonApiService {
    @GET("cards/?pageSize=30")
    suspend fun getPokemons(): Pokemon

    @GET("cards/{id}")
    suspend fun getPokemon(@Path("id") id: String) : PokemonInfo
}

object PokemonApi{
    val RETROFIT_SERVICE: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }
}