package com.example.moviesapplication.ViewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.BuildConfig
import com.example.moviesapplication.data.Movie
import com.example.moviesapplication.interfaces.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val _latestMovies = mutableStateListOf<Movie>()
    val latestMovies: List<Movie> get() = _latestMovies

    private val _upcomingMovies = mutableStateListOf<Movie>()
    val upcomingMovies: List<Movie> get() = _upcomingMovies

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
                val latestResponse = RetrofitInstance.api.getLatestMovies(BuildConfig.API_KEY)
                val upcomingResponse = RetrofitInstance.api.getUpcomingMovies(BuildConfig.API_KEY)

                latestResponse.results?.let { latestMoviesList ->
                    _latestMovies.clear()
                    _latestMovies.addAll(fetchMoviesWithDetails(latestMoviesList))
                }

                upcomingResponse.results?.let { upcomingMoviesList ->
                    _upcomingMovies.clear()
                    _upcomingMovies.addAll(fetchMoviesWithDetails(upcomingMoviesList))
                }
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error fetching movies", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Fetch additional details (duration & genre) for each movie.
     */
    private suspend fun fetchMoviesWithDetails(movies: List<Movie>): List<Movie> {
        return movies.map { movie ->
            try {
                val details = RetrofitInstance.api.getMovieDetails(movie.id, BuildConfig.API_KEY)
                movie.copy(
                    duration = "${details.runtime ?: 0} Minutes",
                    genre = details.genres?.joinToString(" | ") { it.name ?: "" } ?: "Unknown"
                )
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error fetching details for movie ${movie.id}", e)
                movie // Return the original movie object if details fetch fails
            }
        }
    }
}
