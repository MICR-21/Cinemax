package com.example.moviesapplication.screens

import NavigationManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moviesapplication.R

@Composable
fun OnboardingOne(navigationManager: NavigationManager ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(bottom = 4.dp),


        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween

    ) {
        // Top Section - Image

        Image(
            painter = painterResource(id = R.drawable.onboarding1),
            contentDescription = "Person with phone",
            modifier = Modifier.size(500.dp),// Larger image for emphasis
            contentScale = ContentScale.Crop
        )

        // Middle Section - Text
        Column(horizontalAlignment = Alignment.CenterHorizontally, ) {
            Text(
                text = "Lorem ipsum dolor sit amet consecteur esplicit",
                style = TextStyle(
                    fontSize = 22.sp, // Bigger font size
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = Modifier
                    .padding(bottom = 21.dp,)
                    .fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Text(
                text = "Semper in cursus magna et eu varius nunc adipiscing.",
                style = TextStyle(fontSize = 16.sp, color = Color.LightGray),
                modifier = Modifier.padding(horizontal = 16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }

        // Bottom Section - Indicators and Button
        // Navigation Row
        Row(
            modifier = Modifier.padding(bottom = 24.dp)
                .padding(horizontal = 56.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Dots indicator
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(if (index == 1) Color(0xFF1E88E5) else Color.Gray)
                    )
                }
            }

            // Navigation Button
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1E88E5))
                    .clickable { navigationManager.navigateToOnboarding2() },
                contentAlignment = Alignment.Center

            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward, // Replace with custom arrow icon if needed
                    contentDescription = "Next Button",
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                )

            }
        }
    }
}
@Preview
@Composable
fun OnboardingOnePreview() {
    OnboardingOne(navigationManager =NavigationManager(rememberNavController()))
}


