package com.example.moviesapplication.screens

import NavigationManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviesapplication.ViewModel.MovieViewModel

@Composable
fun SearchScreen(
    viewModel: MovieViewModel = viewModel(),
    navigationManager: NavigationManager,
    onItemSelected: (Int) -> Unit
) {
    val latestMovies = viewModel.latestMovies
    val upcomingMovies = viewModel.upcomingMovies
    var query by remember { mutableStateOf("") }
    var selectedGenre by remember{ mutableStateOf("All")}

    val filteredMovies = (latestMovies + upcomingMovies).filter { movie ->
        movie.title.contains(query, ignoreCase = true) &&
                (selectedGenre == "All" || movie.genre ?. contains(selectedGenre, ignoreCase = true) == true)
        movie.title.contains(query, ignoreCase = true) &&
                (selectedGenre == "All" || movie.genre?.contains(selectedGenre, ignoreCase = true) == true)
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navigationManager, selectedItem = 1, onItemSelected = onItemSelected)
        },
        containerColor = Color(0xFF1F1D2B)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF1F1D2B))
        ) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF2A2935)),

                    placeholder = { Text("Search by title, category, years", color = Color.Gray) },
                    leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color.Gray) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF2A2935),
                        unfocusedContainerColor = Color(0xFF2A2935),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (filteredMovies.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "No Results", tint = Color.LightGray, modifier = Modifier.size(64.dp))
                        Text("We Are Sorry, We Can Not Find The Movie :(", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("Find your movie by Title or Actor", color = Color.Gray)
                    }
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
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
}


//@Composable
//fun NoResultsScreen() {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Image(painter = painterResource(id = R.drawable.noresults), contentDescription = "No results")
//        Text(
//            text = "We Are Sorry, We Can Not Find The Movie :(",
//            color = Color.White,
//            textAlign = TextAlign.Center,
//            style = MaterialTheme.typography.bodyLarge
//        )
//        Text(
//            text = "Find your movie by Type title, categories, years, etc",
//            color = Color.Gray,
//            textAlign = TextAlign.Center,
//            style = MaterialTheme.typography.bodyMedium
//        )
//    }
//}
