package br.com.deyvidfernandes.feedbackwebservice.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.security.NoSuchAlgorithmException

import java.security.KeyManagementException

import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*

private const val URL = "https://api.pokemontcg.io/v2/"

private val moshi = Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()

private val retrofit = Retrofit.Builder()
                        .client(getUnsafeOkHttpClient())
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

private fun getUnsafeOkHttpClient(): OkHttpClient? {
    return try {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts = arrayOf<TrustManager>(
            object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        )

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory = sslContext.socketFactory
        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        builder.hostnameVerifier { hostname: String?, session: SSLSession? -> true }
        builder.build()
    } catch (e: KeyManagementException) {
        throw RuntimeException(e)
    } catch (e: NoSuchAlgorithmException) {
        throw RuntimeException(e)
    }
}