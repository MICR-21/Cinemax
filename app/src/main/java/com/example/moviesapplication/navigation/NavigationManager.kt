import androidx.navigation.NavHostController

class NavigationManager(private val navController: NavHostController) {

    // Navigate to Onboarding Screen
    fun navigateToOnboarding() {
        navController.navigate("onboarding") {
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
            launchSingleTop = true
        }
    }

    // Navigate to Home Screen
    fun navigateToHomeScreen() {
        navController.navigate("HomeScreen") {
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
            launchSingleTop = true
        }
    }

    // Navigate to Movie Detail Screen
    fun navigateToMovieDetail(movieId: Int) {
        navController.navigate("detail/$movieId") {
            launchSingleTop = true
        }
    }

    // Navigate to SignUp Screen
    fun navigateToSignUp() {
        navController.navigate("SignUp") {
            launchSingleTop = true
        }
    }

    // Navigate to Login Screen
    fun navigateToLogin() {
        navController.navigate("login") {
            launchSingleTop = true
        }
    }

    // Go back to the previous screen
    fun goBack() {
        if (navController.previousBackStackEntry != null) {
            navController.popBackStack()
        }
    }
}
