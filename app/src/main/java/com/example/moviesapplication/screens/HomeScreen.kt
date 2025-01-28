package com.example.moviesapplication.screens

import NavigationManager
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.moviesapplication.ViewModel.MovieViewModel
import com.example.moviesapplication.data.Movie

@Composable
fun HomeScreen(viewModel: MovieViewModel, navigationManager: NavigationManager) {
    val latestMovies = viewModel.latestMovies
    val upcomingMovies = viewModel.upcomingMovies

    LazyColumn {
        items(latestMovies) { movie ->
            MovieItem(movie = movie, onClick = { navigationManager.navigateToMovieDetail(movie.id) })
        }
        items(upcomingMovies) { movie ->
            MovieItem(movie = movie, onClick = { navigationManager.navigateToMovieDetail(movie.id) })
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500/${movie.posterPath}",
                contentDescription = null
            )
            Text(text = movie.title)
            Text(text = movie.releaseDate)
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(viewModel = viewModel(), navigationManager = NavigationManager(rememberNavController()))
}