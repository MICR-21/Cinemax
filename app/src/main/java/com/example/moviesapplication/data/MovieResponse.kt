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

data class TrailerResponse(
    val results: List<Trailer>
)

data class Trailer(
    val key: String, // YouTube key
    val site: String, // Youtube
    val type: String // Trailer
//    val videoId: String //
) {

}