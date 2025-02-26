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
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.*
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
    auth: FirebaseAuth,
    onItemSelected: (Int) -> Unit // Pass this function
) {
    val latestMovies = viewModel.latestMovies
    val upcomingMovies = viewModel.upcomingMovies
    var query by remember { mutableStateOf("") }
    var selectedGenre by remember { mutableStateOf("All") }

    val scrollState = rememberLazyListState()
    val isScrolled by remember { derivedStateOf { scrollState.firstVisibleItemIndex > 0 } }

    val filteredMovies = (latestMovies + upcomingMovies).filter { movie ->
        movie.title.contains(query, ignoreCase = true) &&
                (selectedGenre == "All" || movie.genre?.contains(selectedGenre, ignoreCase = true) == true)
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navigationManager, selectedItem = 0, onItemSelected = onItemSelected)
        },
        containerColor = Color(0xFF1F1D2B)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF1F1D2B))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Top Section (Shrinks when scrolling)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = if (isScrolled) 16.dp else 24.dp)
                        .scale(if (isScrolled) 0.9f else 1f) // Shrinks when scrolling
                ) {
                    val currentUser = auth.currentUser
                    val userName = currentUser?.displayName ?: "Guest"

                    Text(
                        text = "Welcome, $userName",
                        style = MaterialTheme.typography.titleLarge
                        .copy(
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
                            .background(Color(0xFF2A2935)),
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
                        items(listOf("All", "Comedy", "Animation", "Documentary", "Dramas", "Science Fiction",
                            "Romance", "Family", "Thriller", "Crime", "Adventure", "Horror")) { category ->
                            CategoryChip(
                                category = category,
                                isSelected = category == selectedGenre,
                                onClick = { selectedGenre = category }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }

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
                    state = scrollState,
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
fun CategoryChip(category: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .padding(end = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
        color = if (isSelected) Color(0xFF4CAF50) else Color(0xFF1E88E5)
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
fun MovieItem(movie: Movie, onClick: () -> Unit)
{
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
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillBounds
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
                //year
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = "Year",
                        tint = Color(0xFF66BB6A),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "${movie.releaseDate?.take(4) ?: "Unknown"}",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                    )
                }
                Spacer(modifier = Modifier.height(3.dp))

                //genre
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Icon(
                        imageVector = Icons.Default.Movie,
                        contentDescription = "Genre",
                        tint = Color(0xFFEF5350),
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${movie.genre ?: "Unknown"}",
//
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                    )
                }
                Spacer(modifier = Modifier.height(3.dp))

                //duration
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = "Duration",
                        tint = Color(0xFF1E88E5),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${movie.duration ?: "Unknown"}",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                    )
                }
                Spacer(modifier = Modifier.height(3.dp))

                //rating
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${movie.rating ?: "Unknown"}",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navigationManager: NavigationManager,
                        selectedItem: Int,
                        onItemSelected: (Int) -> Unit)
{
    val items = listOf(
        BottomNavItem("Home", Icons.Filled.Home), //index 0
        BottomNavItem("Search", Icons.Filled.Search), // index 1
        BottomNavItem("Downloads", Icons.Filled.PlayArrow), // index 2
        BottomNavItem("Profile", Icons.Filled.Person) //index 3
    )

    NavigationBar(
        modifier = Modifier.fillMaxWidth().height(90.dp),
        containerColor = Color.Transparent,
        contentColor = Color.White
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    onItemSelected(index) // Updates state
                    when (item.label) {
                        "Home" -> navigationManager.navigateToHomeScreen()
                        "Search" -> navigationManager.navigateToSearchScreen()
                        "Profile" -> navigationManager.navigateToProfileScreen()
//                        "Downloads" -> navigationManager.navigateToDownloadsScreen()
                    }
                },
                icon = {
                    Box(
                        modifier = Modifier.size(30.dp).clip(RoundedCornerShape(16.dp))
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
fun MainScreenWithBottomNav(auth: FirebaseAuth) {
    val navController = rememberNavController()
    val navigationManager = remember { NavigationManager(navController) }

    var selectedItem by rememberSaveable { mutableStateOf(0) } // Keeps track of selected tab

    val onItemSelected: (Int) -> Unit = { index ->
        selectedItem = index
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navigationManager, selectedItem, onItemSelected) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(navController, startDestination = "HomeScreen") {
                composable("HomeScreen") {
                    onItemSelected(0) // Set Home as selected
                    HomeScreen(
                        viewModel = viewModel(),
                        navigationManager = navigationManager,
                        auth = auth,
                        onItemSelected = onItemSelected
                    )
                }
                composable("profileScreen") {
                    onItemSelected(3) // Set Profile as selected
                    ProfileScreen(
                        auth = auth,
                        navigationManager = navigationManager,
                        onItemSelected = onItemSelected
                    )
                }

                composable("search"){
                    onItemSelected(1) // Set Search as selected
                    SearchScreen(
                        viewModel = viewModel(),
                        navigationManager = navigationManager,
                        auth = auth,
                        onItemSelected = onItemSelected
                    )
                }
            }
        }
    }
}

