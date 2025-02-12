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
import com.example.moviesapplication.screens.OnboardingOne
import com.example.moviesapplication.screens.OnboardingThree
import com.example.moviesapplication.screens.OnboardingTwo


class NavigationManager(private val navController: NavHostController) {

    // Navigate to Onboarding1
    fun navigateToOnboarding1() {
        navController.navigate("onboarding1") {
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true
        }
    }

    // Navigate to Onboarding2
    fun navigateToOnboarding2() {
        navController.navigate("onboarding2") {
            launchSingleTop = true
        }
    }

    // Navigate to Onboarding3
    fun navigateToOnboarding3() {
        navController.navigate("onboarding3") {
            launchSingleTop = true
        }
    }

    // Navigate to MovieApp
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