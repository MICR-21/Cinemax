package com.example.moviesapplication.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moviesapplication.R

@Composable
fun OnboardingTwo(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // Background color set to black (or as per the screenshot)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.onboarding2), // Replace with actual resource ID
                contentDescription = "Movie image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(370.dp) // Adjusted size to better fit the screen
                    .clip(RoundedCornerShape(66.dp)) // Added rounded corners for the image
                    .border(14.dp, Color.Black, RoundedCornerShape(66.dp)), // Border for the image
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(54.dp)) // Adjusted spacing
            Text(
                text = "Lorem ipsum dolor sit amet consecteur esplicit",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Semper in cursus magna et eu varius nunc adipiscing.",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.LightGray,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.weight(1f)) // Pushes content upwards
//             Bottom Section - Indicators and Button
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
                        .clickable { navController.navigate("Onboarding3") },
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
}
@Preview
@Composable
fun OnboardingTwoPreview() {
    OnboardingTwo(navController = rememberNavController())
}

