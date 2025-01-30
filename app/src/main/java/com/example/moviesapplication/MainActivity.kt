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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesapplication.ViewModel.MovieViewModel
import com.example.moviesapplication.screens.HomeScreen
import com.example.moviesapplication.screens.MovieDetailScreen
//import com.example.moviesapplication.screens.MovieApp
import com.example.moviesapplication.screens.OnboardingOne
import com.example.moviesapplication.screens.OnboardingThree
import com.example.moviesapplication.screens.OnboardingTwo

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

    NavHost(navController, startDestination = "onboarding1") {
        // Onboarding Screens
        composable("Onboarding1") { OnboardingOne(navigationManager = navigationManager) }
        composable("Onboarding2") { OnboardingTwo(navigationManager = navigationManager)        }
        composable("Onboarding3") { OnboardingThree(navigationManager = navigationManager) }

        // MovieApp Screens
        composable("HomeScreen") {
            HomeScreen(viewModel = viewModel, navigationManager = navigationManager)
        }
        composable("detail/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
            if (movieId != null) {
                val movie = viewModel.latestMovies.find { it.id == movieId }
                    ?: viewModel.upcomingMovies.find { it.id == movieId }
                if (movie != null) {
                    MovieDetailScreen(movie = movie, navigationManager = navigationManager)
                }
            }
        }
    }
}









