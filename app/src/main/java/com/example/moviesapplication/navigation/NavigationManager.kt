import androidx.navigation.NavController
import androidx.navigation.NavHostController

class NavigationManager(private val navController: NavHostController) {

    // Navigate to the MainScreen
    fun navigateToMain() {
        navController.navigate("main") {
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true
        }
    }

    // Navigate to the MovieApp
    fun navigateToMovieApp() {
        navController.navigate("MovieApp") {
            launchSingleTop = true
        }
    }

    // Navigate to the Movie Detail screen
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