package com.example.moviesapplication
import NavigationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.Coil
import coil.ImageLoader
import coil.util.DebugLogger
import com.example.moviesapplication.ViewModel.MovieViewModel
import com.example.moviesapplication.screens.HomeScreen
import com.example.moviesapplication.screens.LoginScreen
import com.example.moviesapplication.screens.MovieDetailScreen
import com.example.moviesapplication.screens.OnboardingScreen
import com.example.moviesapplication.screens.SignUpScreen
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth  // Firebase Authentication instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()  // Initialize FirebaseAuth
        enableEdgeToEdge()
        setContent {
            MovieApp2(auth)
            Coil.setImageLoader(
                ImageLoader.Builder(this)
                    .logger(DebugLogger()) // Enable debug logs
                    .build()
            )
        }
    }
}

@Composable
fun MovieApp2(auth: FirebaseAuth, viewModel: MovieViewModel = viewModel()) {
    val navController = rememberNavController()
    val navigationManager = remember { NavigationManager(navController) }

    NavHost(navController, startDestination = "login") {  // Start with Login screen
        composable("onboarding") { OnboardingScreen(navigationManager = navigationManager) }
        composable("HomeScreen") { HomeScreen(viewModel = viewModel, navigationManager = navigationManager,auth) }
        composable("SignUp") { SignUpScreen(navigationManager = navigationManager, auth) }
        composable("login") { LoginScreen(navigationManager = navigationManager, auth ) }
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












