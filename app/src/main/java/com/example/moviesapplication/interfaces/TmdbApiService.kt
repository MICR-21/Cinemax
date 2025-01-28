package com.example.moviesapplication.interfaces

import com.example.moviesapplication.data.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApiService {
    @GET("movie/now_playing")
    suspend fun getLatestMovies(@Query("api_key")
                                    apiKey : String):MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String): MovieResponse






}