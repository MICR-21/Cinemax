package com.example.moviesapplication.data
import com.google.gson.annotations.SerializedName


data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("duration") val duration: String,
    @SerializedName("genre") val genre:String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val rating: String,
    @SerializedName("popularity") val popularity: String,
    @SerializedName("vote_count") val voteCount: Int,
    ){
    fun getPosterUrl(): String {
        return "https://image.tmdb.org/t/p/w500$posterPath"
    }

}

