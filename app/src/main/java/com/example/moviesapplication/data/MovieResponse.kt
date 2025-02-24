package com.example.moviesapplication.data

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("genres") val genres: List<Genre>?,
    @SerializedName("runtime") val runtime: Int?,
    val results: List<Movie>
)
data class Genre (
    @SerializedName("name") val name: String?
)