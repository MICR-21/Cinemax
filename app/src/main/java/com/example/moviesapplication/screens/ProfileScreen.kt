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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.moviesapplication.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(navigationManager: NavigationManager, auth: FirebaseAuth, onItemSelected: (Int) -> Unit) {
    LaunchedEffect(Unit) { onItemSelected(3) } // Ensure Profile tab is selected when screen loads

    Scaffold(
        bottomBar = { BottomNavigationBar(navigationManager, selectedItem = 3, onItemSelected = onItemSelected) },
        containerColor = Color((0xFF141622))
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1F1D2B))
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()) // Enables scrolling
        ){
            val currentUser = auth.currentUser
            val userName = currentUser?.displayName ?: "Guest"
            val userEmail = currentUser?.email ?: "No email available"

            // Top Profile Info
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1F1D2B), shape = RoundedCornerShape(12.dp))
                    .padding(top = 26.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_placeholder),
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(50.dp))
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = userName,
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif
                        )
                        Text(
                            text = userEmail,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontFamily = FontFamily.Serif
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Profile",
                        tint = Color.White,
                        modifier = Modifier
                            .clickable { navigationManager.navigateToEditProfile() }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Premium Member Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(Color(0xFFFF9800), shape = RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Premium Member\nNew movies are coming for you,\nDownload Now!",
                    fontSize = 14.sp,
                    color = Color.White,
                    fontFamily = FontFamily.Serif
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Account & Settings Options
            Column(modifier = Modifier.padding(horizontal = 5.dp)) {
                SectionHeader("Account")
                ProfileOption("Member", Icons.Filled.Person) { navigationManager.navigateToMember() }
                ProfileOption("Change Password", Icons.Filled.Lock) { navigationManager.navigateToForgotPassword() }
                ProfileOption("Payment", Icons.Filled.MonetizationOn) { navigationManager.navigateToPayment() }

                Spacer(modifier = Modifier.height(20.dp))

                SectionHeader("General")
                ProfileOption("Notifications", Icons.Default.Notifications) { navigationManager.navigateToNotifications() }
                ProfileOption("Language", Icons.Filled.Language) { navigationManager.navigateToLanguage() }
                ProfileOption("Country", Icons.Filled.Public) { navigationManager.navigateToCountry() }
                ProfileOption("Clear Cache", Icons.Filled.Delete) { navigationManager.navigateToClearCache() }

                Spacer(modifier = Modifier.height(20.dp))

                SectionHeader("More")
                ProfileOption("Legal and Policies", Icons.Filled.Gavel) { navigationManager.navigateToLegal() }
                ProfileOption("Help & Feedback", Icons.Filled.Help) { navigationManager.navigateToHelp() }
                ProfileOption("About Us", Icons.Filled.Info) { navigationManager.navigateToAboutUs() }

                Spacer(modifier = Modifier.height(30.dp))

                // Logout Button
                Button(
                    onClick = {
                        auth.signOut()
                        navigationManager.navigateToLogin()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF217FAB),
                        disabledContainerColor = Color.Yellow,

                        )
                ) {
                    Text("Log Out", color = Color.White, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

// Section Header
@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 16.sp,
        color = Color.Gray,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

// Profile Option Item
@Composable
fun ProfileOption(label: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = label, tint = Color.White, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = label, fontSize = 14.sp, color = Color.White, fontFamily = FontFamily.Serif)
    }
}



















