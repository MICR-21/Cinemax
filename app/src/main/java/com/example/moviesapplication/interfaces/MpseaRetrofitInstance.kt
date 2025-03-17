package com.example.moviesapplication.interfaces

import android.util.Log
import okhttp3.OkHttpClient
import com.example.moviesapplication.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MpesaRetrofitInstance {

    private const val BASE_URL = ""

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Logs request & response
        }).addInterceptor { chain ->
            val request = chain.request()
            Log.d("MpesaRequest", "Request: ${request.url}, Headers: ${request.headers}")
            val response = chain.proceed(request)
            Log.d("MpesaResponse", "Response Code: ${response.code}, Body: ${response.peekBody(2048).string()}")
            response
        }.build()


    val api: MpesaApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MpesaApiService::class.java)
    }
}