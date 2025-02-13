import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesapplication.ViewModel.MovieViewModel
import com.example.moviesapplication.screens.HomeScreen
import com.example.moviesapplication.screens.MovieDetailScreen



class NavigationManager(private val navController: NavHostController) {

    // Navigate to Onboarding Screen
    fun navigateToOnboarding() {
        navController.navigate("onboarding") {
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true
        }
    }

    // Navigate to Home Screen
    fun navigateToHomeScreen() {
        navController.navigate("HomeScreen") {
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true
        }
    }

    // Navigate to Movie Detail screen
    fun navigateToMovieDetail(movieId: Int) {
        navController.navigate("detail/$movieId") {
            launchSingleTop = true
        }
    }

    // Go back to the previous screen
    fun goBack() {
        navController.popBackStack()
    }
}
