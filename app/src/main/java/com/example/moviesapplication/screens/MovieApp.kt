package com.example.moviesapplication.screens

import NavigationManager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesapplication.ViewModel.MovieViewModel
import com.example.moviesapplication.data.Movie

@Composable
fun MainScreen(navigationManager: NavigationManager) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Movie App!")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navigationManager.navigateToMovieApp() }) {
            Text("Get Started")
        }
    }
}

@Composable
fun MovieApp(viewModel: MovieViewModel = viewModel()) {
    val navController = rememberNavController()
    val navigationManager = remember { NavigationManager(navController) }

    NavHost(navController, startDestination = "main") {
        // MainScreen destination
        composable("main") {
            MainScreen(navigationManager = navigationManager)
        }

        // MovieApp destination
        composable("movieApp") {
            HomeScreen(viewModel = viewModel, navigationManager = navigationManager)
        }

        // MovieDetail destination
        composable("detail/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toInt()
            val movie = viewModel.latestMovies.find { it.id == movieId }
                ?: viewModel.upcomingMovies.find { it.id == movieId }
            if (movie != null) {
                MovieDetailScreen(movie = movie, navigationManager = navigationManager)
            }
        }
    }
}