package com.example.moviesapplication.screens

import NavigationManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.LocalMovies
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesapplication.R
import com.example.moviesapplication.data.Movie

@Composable
fun MovieDetailScreen(movie: Movie, navigationManager: NavigationManager,
                      selectedItem: Int, onItemSelected: (Int) -> Unit) {


    var showShareDialog by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = { BottomNavigationBar(navigationManager = navigationManager,selectedItem, onItemSelected) },
        containerColor = Color(0xFF1F1D2B)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1F1D2B))
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navigationManager.goBack() }
                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(138.dp))
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Row {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = movie.title,
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontFamily = FontFamily.Serif
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Image(
                    painter = rememberAsyncImagePainter(movie.getPosterUrl()),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(top = 20.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Spacer(modifier = Modifier.height(16.dp))

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
                        text = "${movie.duration ?: "N/A"} | ",
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = "releaseDate",
                        tint = Color(0xFF66BB6A),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${movie.releaseDate?.take(4)} | ",
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray)
                    )

                    Icon(
                        imageVector = Icons.Default.LocalMovies,
                        contentDescription = "Movie",
                        tint = Color(0xFF1E88E5),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${movie.genre?.take(8)} ",
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    // PLAY BUTTON
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth(0.28f)
                            .height(50.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))
                    ) {
                        Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Play", tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Play", style = MaterialTheme.typography.bodyLarge
                            .copy(fontWeight = FontWeight.Bold), color = Color.White,
                            fontFamily = FontFamily.Serif

                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    //DONWLOAD BUTTON
                    Button(
                        onClick = {
//                            navigationManager.navigateToDownloadScreen(movie)
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.28f)
                            .height(40.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF252836))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = "Download",
                            tint = Color(0xFFFF9800)

                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    //SHARE BUTTON
                    Button(
                        onClick = { showShareDialog = true },
                        modifier = Modifier
                            .fillMaxWidth(0.40f)
                            .height(40.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF252836))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = Color(0xFF12CDD9),

                            )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))



                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth(0.25f)
                            .height(40.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E5FF))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFFF9800),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = movie.rating ?.take(3) ?: "Unknown",
                            style = MaterialTheme.typography.titleLarge.copy(color = Color(0xFFFF9800)),
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
                Spacer(modifier = Modifier.height(24.dp))

                    // STORY LINE
                Text(
                    text = "Story Line",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodyLarge
                        .copy(color = Color.LightGray),
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                //SHARE ICON

                if (showShareDialog) {
                    AlertDialog(
                        containerColor = Color(0xFF252836),
                        onDismissRequest = { showShareDialog = false },
                        title = {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ){
                                Text(
                                    text = "Share to",
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                )
                            }
                        }
                        ,
                        text = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                            ) {
                                IconButton(onClick = { /* Share to Facebook */ }) {
                                    Image(
                                        painter = painterResource(id = R.drawable.facebookshare),
                                        contentDescription = "Facebook",
                                        modifier = Modifier.size(90.dp),
                                    )
                                }
                                IconButton(onClick = { /* Share to Messenger */ }) {
                                    Image(painter = painterResource(id = R.drawable.messageshare),
                                        contentDescription = "Messenger",
                                        modifier = Modifier.size(90.dp)
                                    )
                                }
                                IconButton(onClick = { /* Share to Telegram */ }) {
                                    Image(painter = painterResource(id = R.drawable.telegramshare),
                                        contentDescription = "Telegram",
                                        modifier = Modifier.size(90.dp)
                                    )
                                }
                            }
                        },
                        confirmButton = {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ){
                                Button(
                                    onClick = { showShareDialog = false }) {
                                    Text("Close")
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}


