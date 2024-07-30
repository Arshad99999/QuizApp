package ars.example.quizzapp

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object serviceBuilder {

    private const val BASE_URL = "https://the-trivia-api.com/"

    private val okhttpclient = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttpclient)
        .build()

    fun<T>getService(serviceType:Class<T>):T{
        return retrofit.create(serviceType)
    }
}