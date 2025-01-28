package com.example.moviesapplication.screens

import NavigationManager
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.moviesapplication.data.Movie

@Composable
fun MovieDetailScreen(movie: Movie, navigationManager: NavigationManager) {
    Column {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/${movie.posterPath}",
            contentDescription = null
        )
        Text(text = movie.title)
        Text(text = movie.overview)
        Text(text = movie.releaseDate)
    }
}


@Preview
@Composable
fun MovieDetailScreenPreview() {
    MovieDetailScreen(navigationManager = NavigationManager(rememberNavController()),movie = Movie(1, "Title", "Overview", "posterPath", "releaseDate"))
}