package com.example.moviesapplication.ViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.data.Movie
import com.example.moviesapplication.interfaces.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val _latestMovies = mutableStateListOf<Movie>()
    val latestMovies = _latestMovies  // Keep as mutable state

    private val _upcomingMovies = mutableStateListOf<Movie>()
    val upcomingMovies = _upcomingMovies  // Keep as mutable state

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val latestResponse = RetrofitInstance.api.getLatestMovies("cc76364ac8a0d9232dcbc2d487631e8c")
                Log.d("MovieViewModel", "Latest Movies Response: $latestResponse")

                latestResponse.results?.let {
                    _latestMovies.clear()
                    _latestMovies.addAll(it)
                }

                val upcomingResponse = RetrofitInstance.api.getUpcomingMovies("cc76364ac8a0d9232dcbc2d487631e8c")
                Log.d("MovieViewModel", "Upcoming Movies Response: $upcomingResponse")

                upcomingResponse.results?.let {
                    _upcomingMovies.clear()
                    _upcomingMovies.addAll(it)
                }
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error fetching movies", e)
            }
        }
    }
}
