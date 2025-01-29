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

    // Show a loading spinner while data is being fetched
    if (latestMovies.isEmpty() && upcomingMovies.isEmpty()) {
        Box(

            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No movies available", color = Color.Red )
            CircularProgressIndicator(color = Color.Black)
        }
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFF1F1D2B))
    ) {
        item {
            // Greeting Section
            Text(
                text = "Hello, Smith",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Let's stream your favorite movie",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Search Bar Fix
//
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                placeholder = {
                    Text(
                        text = "Search a title",
                        color = Color.Black
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Black
                    )
                },

                colors = TextFieldDefaults.colors
                    (
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.Gray,
                )
            )
//coil for imagees
            Spacer(modifier = Modifier.height(24.dp))

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
            Spacer(modifier = Modifier.height(24.dp))

            // Most Popular Section
            Text(
                text = "Most Popular",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Movie List
        items(latestMovies + upcomingMovies) { movie ->
            MovieItem(movie = movie, onClick = { navigationManager.navigateToMovieDetail(movie.id) })
        }
        // Bottom Navbar

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
                model = "${movie.posterPath}",
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


//@Composable
//fun BottomNavigationBar(navController: NavController, modifier: Modifier = Modifier) {
//    var selectedItem by remember { mutableStateOf(0) }
//
//    NavigationBar(
//        modifier = modifier,
//        containerColor = Color(0xFF7B1FA2),
//        contentColor = Color.White
//    ) {
//        val items = listOf(
//            BottomNavItem("Home", Icons.Filled.Home),
//            BottomNavItem("Search", Icons.Filled.Search),
//            BottomNavItem("Downloads", Icons.Default.PlayArrow),
//            BottomNavItem("Profile", Icons.Filled.Person)
//        )
//
//        items.forEachIndexed { index, item ->
//            NavigationBarItem(
//                icon = { Icon(item.icon, contentDescription = item.label) },
//                label = { Text(text = item.label) },
//                selected = selectedItem == index,
//                onClick = { selectedItem = index },
//                colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = Color.White,
//                    unselectedIconColor = Color.LightGray,
//                    indicatorColor = Color(0xFF673AB7)
//                )
//            )
//        }
//    }
//}

data class BottomNavItem(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

//@Composable
//fun MainScreenWithBottomNav() {
//    val navController = rememberNavController()
//
//    Scaffold(
//        bottomBar = { BottomNavigationBar(navController) }
//    ) { paddingValues ->
//        Box(modifier = Modifier.padding(paddingValues)) {
//            // Add your screen content here
//        }
//    }
//}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(viewModel = viewModel(), navigationManager = NavigationManager(rememberNavController()))
}