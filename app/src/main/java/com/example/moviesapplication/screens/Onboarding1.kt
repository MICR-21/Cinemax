package com.example.moviesapplication.screens

import NavigationManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.moviesapplication.R

@Composable
fun OnboardingScreen(navigationManager: NavigationManager) {
    var currentPage by remember { mutableStateOf(0) }

    val onboardingPages = listOf(
        OnboardingPage(
            imageRes = R.drawable.onboarding1,
            title = "Lorem ipsum dolor sit amet consecteur esplicit",
            description = "Semper in cursus magna et eu varius nunc adipiscing."
        ),
        OnboardingPage(
            imageRes = R.drawable.onboarding2,
            title = "Discover Amazing Movies",
            description = "Explore a wide range of movies and stream your favorites."
        ),
        OnboardingPage(
            imageRes = R.drawable.onboarding3,
            title = "Enjoy with Friends & Family",
            description = "Watch together, share your favorite moments, and have fun."
        )
    )

    val isLastPage = currentPage == onboardingPages.lastIndex

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F1D2B))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Display Image
            Image(
                painter = painterResource(id = onboardingPages[currentPage].imageRes),
                contentDescription = "Onboarding Image",
                modifier = Modifier
                    .padding(top = 60.dp)
                    .fillMaxWidth()
                    .height(500.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Display Title
            Text(
                text = onboardingPages[currentPage].title,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Display Description
            Text(
                text = onboardingPages[currentPage].description,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.LightGray,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            // Page Indicator & Next Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Dot Indicators
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    repeat(onboardingPages.size) { index ->
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(if (index == currentPage) Color(0xFF1E88E5) else Color.Gray)
                        )
                    }
                }

                // Back Button
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF1E88E5))
                        .clickable {
                            navigationManager.goBack()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Next Button",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
                // Back Button
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF1E88E5))
                        .clickable {
                            if (isLastPage) {
                                navigationManager.navigateToHomeScreen()
                            } else {
                                currentPage++
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "next Button",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

// Data class to store onboarding page details
data class OnboardingPage(
    val imageRes: Int,
    val title: String,
    val description: String
)

//
@Preview
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(navigationManager = NavigationManager(rememberNavController()))
}
