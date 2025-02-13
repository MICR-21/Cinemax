package com.example.moviesapplication

import coil.Coil
import coil.ImageLoader
import coil.util.DebugLogger
import NavigationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
//import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesapplication.ViewModel.MovieViewModel
import com.example.moviesapplication.screens.HomeScreen
import com.example.moviesapplication.screens.MovieDetailScreen
import com.example.moviesapplication.screens.OnboardingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieApp2()
            Coil.setImageLoader(
                ImageLoader.Builder(this)
                    .logger(DebugLogger()) // Enable debug logs
                    .build()
            )
//            HomeScreen(viewModel = MovieViewModel(), navigationManager = NavigationManager(rememberNavController()))
        }
    }

}
@Composable
fun MovieApp2(viewModel: MovieViewModel = viewModel()) {
        val navController = rememberNavController()
        val navigationManager = remember { NavigationManager(navController) }
        val viewModel: MovieViewModel = viewModel()  // Correct placement

        NavHost(navController, startDestination = "onboarding") {  // Single onboarding screen
            // Single Onboarding Screen
            composable("onboarding") { OnboardingScreen(navigationManager = navigationManager) }

            // MovieApp Screens
            composable("HomeScreen") {
                HomeScreen(viewModel = viewModel, navigationManager = navigationManager)
            }
            composable("detail/{movieId}") { backStackEntry ->
                val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
                val movie = movieId?.let {
                    viewModel.latestMovies.find { it.id == movieId }
                        ?: viewModel.upcomingMovies.find { it.id == movieId }
                }
                if (movie != null) {
                    MovieDetailScreen(movie = movie, navigationManager = navigationManager)
                }
            }
        }
    }









