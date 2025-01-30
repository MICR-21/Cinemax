package com.example.moviesapplication.screens

import NavigationManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.moviesapplication.ViewModel.MovieViewModel
import com.example.moviesapplication.data.Movie


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MovieViewModel = viewModel(), navigationManager: NavigationManager) {
    val latestMovies = viewModel.latestMovies
    val upcomingMovies = viewModel.upcomingMovies

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = rememberNavController()) },
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
                // Greeting Section
                Text(
                    text = "Hello, Smith",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Let's stream your favorite movie",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.LightGray)
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Search Bar
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
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

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(latestMovies + upcomingMovies) { movie ->
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
    // Default to a fallback value if title is null

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column {
            AsyncImage(
                model = "https://api.themoviedb.org/3/${movie.posterPath}",
                contentDescription = "${movie.overview}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
//                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,  // Using safe value (not null)
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = movie.releaseDate ?: "Unknown Release",  // Handle release date safely
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Black
                ),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavController, modifier: Modifier = Modifier) {
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
                onClick = { selectedItem = index },
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


data class BottomNavItem(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)


@Composable
fun MainScreenWithBottomNav() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            HomeScreen(navigationManager = NavigationManager(navController))
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(viewModel = viewModel(), navigationManager = NavigationManager(rememberNavController()))
}