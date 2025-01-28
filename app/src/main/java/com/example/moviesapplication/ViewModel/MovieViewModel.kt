package com.example.moviesapplication.ViewModel

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.data.Movie
import com.example.moviesapplication.interfaces.RetrofitInstance
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {
    private val _latestMovies = mutableStateListOf<Movie>()
    val latestMovies: List<Movie> get() = _latestMovies

    private val _upcomingMovies = mutableStateListOf<Movie>()
    val upcomingMovies: List<Movie> get() = _upcomingMovies

    init {
        viewModelScope.launch {
            val latest = RetrofitInstance.api.getLatestMovies("cc76364ac8a0d9232dcbc2d487631e8c")
            _latestMovies.addAll(latest.results)

            val upcoming = RetrofitInstance.api.getUpcomingMovies("cc76364ac8a0d9232dcbc2d487631e8c")
            _upcomingMovies.addAll(upcoming.results)
        }
    }
}