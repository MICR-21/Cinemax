package com.example.moviesapplication.ViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.BuildConfig
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
<<<<<<< HEAD
                val latestResponse = RetrofitInstance.api.getLatestMovies(BuildConfig.API_KEY)
=======
                val latestResponse = RetrofitInstance.api.getLatestMovies("YOUR_API_KEY")
>>>>>>> 7e23bfc74ca691cb041b47c7720822f56ad10b0a
                Log.d("MovieViewModel", "Latest Movies Response: $latestResponse")

                latestResponse.results?.let {
                    _latestMovies.clear()
                    _latestMovies.addAll(it)
                }

<<<<<<< HEAD
                val upcomingResponse = RetrofitInstance.api.getUpcomingMovies(BuildConfig.API_KEY)
=======
                val upcomingResponse = RetrofitInstance.api.getUpcomingMovies("YOUR_API_KEY")
>>>>>>> 7e23bfc74ca691cb041b47c7720822f56ad10b0a
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
