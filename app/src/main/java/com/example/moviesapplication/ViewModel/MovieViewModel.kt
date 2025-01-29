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
    val latestMovies: List<Movie> get() = _latestMovies

    private val _upcomingMovies = mutableStateListOf<Movie>()
    val upcomingMovies: List<Movie> get() = _upcomingMovies

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val latestResponse = RetrofitInstance.api.getLatestMovies("cc76364ac8a0d9232dcbc2d487631e8c")
                if (latestResponse.results != null) {  // Ensure results is not null
                    _latestMovies.clear()
                    _latestMovies.addAll(latestResponse.results)
                } else {
                    // Handle case where the results are null (perhaps show an error message)
                    Log.i("MovieViewModel", "latestResponse.results is null") ///informative message
//                  debugging messages -> Log.d

                }

                val upcomingResponse = RetrofitInstance.api.getUpcomingMovies("cc76364ac8a0d9232dcbc2d487631e8c")
                if (upcomingResponse.results != null) {  // Ensure results is not null
                    _upcomingMovies.clear()
                    _upcomingMovies.addAll(upcomingResponse.results)
                } else {
                    // Handle case where the results are null
                }

            } catch (e: Exception) {
                e.printStackTrace() // Log any exceptions
            }
        }
    }

}
