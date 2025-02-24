package com.example.moviesapplication.screens

import NavigationManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.moviesapplication.ViewModel.MovieViewModel
import com.example.moviesapplication.data.Movie
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.max



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MovieViewModel = viewModel(), navigationManager: NavigationManager,
               auth: FirebaseAuth) {
    val latestMovies = viewModel.latestMovies
    val upcomingMovies = viewModel.upcomingMovies

    var query by remember { mutableStateOf("") } // State for search query
    val filteredMovies = (latestMovies + upcomingMovies).filter {
        it.title.contains(query, ignoreCase = true)
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navigationManager = navigationManager) },
        containerColor = Color(0xFF1F1D2B) // Dark background
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF1F1D2B)) // Match background color
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Show the logged-in user's email
                val currentUser = auth.currentUser
                val userName = currentUser?.displayName ?: "Guest"

                Text(
                    text = "Welcome, $userName",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Let's stream your favorite movie",
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.LightGray)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Search Bar
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF2A2935)), // Darker background
                    placeholder = {
                        Text("Search a title", color = Color.Gray)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.Gray
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF2A2935),
                        unfocusedContainerColor = Color(0xFF2A2935),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Categories Section
                Text(
                    text = "Categories",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow {
                    items(listOf("All", "Comedy", "Animation", "Documentary", "Dramas")) { category ->
                        CategoryChip(category = category)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Most Popular Section
                Text(
                    text = "Most Popular",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Display filtered movies instead of all movies
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredMovies) { movie ->
                        MovieItem(movie = movie, onClick = {
                            navigationManager.navigateToMovieDetail(movie.id)
                        })
                    }
                }
            }
        }
    }
}


@Composable
fun CategoryChip(category: String) {
    Surface(
        modifier = Modifier
            .padding(end = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { },
        color = Color(0xFF1E88E5)
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.White
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    val title = movie.title ?: "Unknown Title"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF252836)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp) // Spacing between image & text
        ) {
            Image(
                painter = rememberAsyncImagePainter(movie.getPosterUrl()),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp) // Fixed size for consistency
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.weight(1f), // Takes available space
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title.take(25),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "Year: ${movie.releaseDate?.take(4) ?: "Unknown"}",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )

                Text(
                    text = "Popularity: ${movie.popularity?: "Unknown"}",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )

                Text(
                    text = "Vote Count: ${movie.voteCount?: "Unknown"}",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
                Text(
                    text = "Genre: ${movie.genre?: "Unknown"}",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
                Text(
                    text = "Duration: ${movie.duration?: "Unknown"}",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
                Text(
                    text = "Rating: ${movie.rating?: "Unknown"}",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navigationManager: NavigationManager, modifier: Modifier = Modifier) {
    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf(
        BottomNavItem("Home", Icons.Filled.Home),
        BottomNavItem("Search", Icons.Filled.Search),
        BottomNavItem("Downloads", Icons.Filled.PlayArrow),
        BottomNavItem("Profile", Icons.Filled.Person)
    )

    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(Color(0xFF1F1D2B)), // Dark background
        containerColor = Color.Transparent,
        contentColor = Color.White
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    when (item.label) {
                        "Profile" -> navigationManager.navigateToProfileScreen() // ✅ Navigate to Profile Screen
                        "Home" -> navigationManager.navigateToHomeScreen() // ✅ Add Home navigation
//                        "Search" -> navigationManager.navigateToSearchScreen() // ✅ Add Search navigation
//                        "Downloads" -> navigationManager.navigateToDownloadsScreen() // ✅ Add Downloads navigation
                    }
                },
                icon = {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (selectedItem == index) Color(0xFF00BCD4) else Color.Transparent),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            tint = if (selectedItem == index) Color.White else Color.Gray
                        )
                    }
                },
                label = {
                    if (selectedItem == index) {
                        Text(text = item.label, color = Color(0xFF00BCD4))
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}



data class BottomNavItem(val label: String, val icon: ImageVector)

@Composable
fun MainScreenWithBottomNav(auth: FirebaseAuth) { // Pass auth as a parameter
    val navController = rememberNavController()
    val navigationManager = remember { NavigationManager(navController) }

    Scaffold(
        bottomBar = { BottomNavigationBar(navigationManager = navigationManager) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(navController, startDestination = "login") {
                composable("HomeScreen") {
                    HomeScreen(
                        viewModel = viewModel(),
                        navigationManager = navigationManager,
                        auth = auth
                    )
                }
                composable("SignUp") {
                    SignUpScreen(
                        navigationManager = navigationManager,
                        auth = auth // Pass auth here
                    )
                }
                composable("login") {
                    LoginScreen(
                        navigationManager = navigationManager,
                        auth = auth // Pass auth here
                    )
                }
                composable("profileScreen")
                { ProfileScreen(auth = auth, navigationManager = navigationManager) }

                composable("forgotPassword"){ ResetPasswordScreen(
                    navigationManager = navigationManager
                )}
//                composable("otp/{email}") { backStackEntry ->
//                    val email = backStackEntry.arguments?.getString("email") ?: ""
//                    VerificationScreen(navigationManager, email)
//                }

            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(viewModel = viewModel(), navigationManager = NavigationManager(rememberNavController()),
        auth = FirebaseAuth.getInstance())
}