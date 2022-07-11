package com.example.dependencyinjection.data.remote.retrofit

import com.example.dependencyinjection.data.remote.parsedata.ResponseRemoteCharacter
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("character")
    suspend fun getCharacters(): Response<ResponseRemoteCharacter>


    companion object {
        private fun getClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(15, TimeUnit.SECONDS)


        fun createApiService(): ApiService {
            return Retrofit.Builder()
                .client(getClientBuilder().build())
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
        }

    }
}