package com.example.moviesapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesapplication.screens.MovieApp
import com.example.moviesapplication.screens.OnboardingOne
import com.example.moviesapplication.screens.OnboardingThree
import com.example.moviesapplication.screens.OnboardingTwo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieApp()
            }
        }
    }

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    SetupNavGraph(navController = navController)
    }


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "onboarding1") {
        composable("onboarding1") {
            OnboardingOne(navController)
        }
        composable("onboarding2") {
            OnboardingTwo(navController)
        }
        composable("onboarding3") {
            OnboardingThree(navController)
        }
    }
}
@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}



