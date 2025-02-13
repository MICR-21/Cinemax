//package com.example.moviesapplication.screens
//
//
//import NavigationManager
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowForward
//import androidx.compose.material.icons.filled.Info
//import androidx.compose.material.icons.filled.Star
//import androidx.compose.material.icons.filled.Warning
//import androidx.compose.material3.Button
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.example.moviesapplication.R
//
//@Composable
//fun OnboardingThree(navigationManager: NavigationManager) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFF1C1C1E)) // Background color to match dark theme
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            // Popcorn Image
//            Box(
//                modifier = Modifier
//                    .size(320.dp)
//                    .background(
//                        color = Color(0xFF1C1C1E),
//                        shape = RoundedCornerShape(16.dp)
//                    )
//                    .padding(8.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.onboarding3), // Replace with actual resource ID
//                    contentDescription = "Person with popcorn",
//                    modifier = Modifier
//                        .fillMaxSize()
//                )
//            }
//            Spacer(modifier = Modifier.height(24.dp))
//
//            // Rating and Duration Row
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 32.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Star, // Replace with custom star icon if needed
//                        contentDescription = "Rating Icon",
//                        tint = Color.Yellow,
//                        modifier = Modifier.size(32.dp)
//                    )
//                    Spacer(modifier = Modifier.height(4.dp))
//                    Text(
//                        text = "Rating",
//                        style = TextStyle(color = Color.White, fontSize = 14.sp)
//                    )
//                    Text(
//                        text = "9 / 10",
//                        style = TextStyle(
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 16.sp,
//                            color = Color.White
//                        )
//                    )
//                }
//
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.timer) ,
//                        contentDescription = "Duration Icon",
//                        modifier = Modifier.size(32.dp),
//                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.Green)
//                    )
//
//
//                    Spacer(modifier = Modifier.height(4.dp))
//                    Text(
//                        text = "Duration",
//                        style = TextStyle(color = Color.White, fontSize = 14.sp)
//                    )
//                    Text(
//                        text = "1h 20m",
//                        style = TextStyle(
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 16.sp,
//                            color = Color.White
//                        )
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(32.dp))
//
//            // Text Section
//            Text(
//                text = "Lorem ipsum dolor sit amet\nconsectetur esplicit",
//                style = TextStyle(
//                    color = Color.White,
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.ExtraBold,
//                    textAlign = TextAlign.Center
//                )
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            Text(
//                text = "Semper in cursus magna et eu varius nunc adipiscing. Elementum justo, laoreet id sem semper parturient.",
//                style = TextStyle(
//                    color = Color.LightGray,
//                    fontSize = 14.sp,
//                    textAlign = TextAlign.Center
//                ),
//                modifier = Modifier.padding(horizontal = 16.dp)
//            )
//
//            Spacer(modifier = Modifier.weight(1f))
//
//            // Navigation Row
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 24.dp)
//                    .padding(horizontal = 56.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                // Dots indicator
//                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//                    repeat(3) { index ->
//                        Box(
//                            modifier = Modifier
//                                .size(8.dp)
//                                .clip(CircleShape)
//                                .background(if (index == 1) Color(0xFF1E88E5) else Color.Gray)
//                        )
//                    }
//                }
//
//                // Navigation Button
//                Box(
//                    modifier = Modifier
//                        .size(56.dp)
//                        .clip(RoundedCornerShape(16.dp))
//                        .background(Color(0xFF1E88E5))
//                        .clickable {
//                            try{
//                                navigationManager.navigateToHomeScreen()
//                            }catch (e: Exception){
//                                e.printStackTrace()
//                            }
//                        }, //homescreen navigation
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.ArrowForward, // Replace with custom arrow icon if needed
//                        contentDescription = "Next Button",
//                        tint = Color.White,
//                        modifier = Modifier.size(24.dp)
//
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun OnboardingThreePreview() {
//    OnboardingThree(navigationManager = NavigationManager(rememberNavController()) )
//}
