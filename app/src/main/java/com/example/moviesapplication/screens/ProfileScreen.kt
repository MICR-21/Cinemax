package com.example.moviesapplication.screens

import NavigationManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.moviesapplication.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(navigationManager: NavigationManager, auth: FirebaseAuth) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141622))
            .padding(top = 50.dp)
    ) {
        val currentUser = auth.currentUser
        val userName = currentUser?.displayName ?: "Guest"
        val userEmail = currentUser?.email ?: "No email available"

//         Top Profile Info
        Box(
            modifier = Modifier
                .fillMaxWidth()
//                .padding(16.dp)
                .background(Color(0xFF1F1D2B), shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.profile_placeholder), // Replace with actual profile picture if available
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(50.dp))
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = userName, fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
                    Text(text = userEmail, fontSize = 14.sp, color = Color.Gray)
                }
            }
        }

        // Premium Member Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
//                .padding(horizontal = 6.dp)
                .padding(top = 16.dp)
                .background(Color(0xFFFF9800), shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "Premium Member\nNew movies are coming for you,\nDownload Now!",
                fontSize = 14.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Account & Settings Options
        Column(modifier = Modifier.padding(horizontal = 5.dp)) {
            SectionHeader("Account")
            ProfileOption("Member", Icons.Filled.Person)
            ProfileOption("Change Password", Icons.Filled.Lock)

            SectionHeader("General")
            ProfileOption("Language", Icons.Filled.Language)
            ProfileOption("Country", Icons.Filled.Public)

            SectionHeader("More")
            ProfileOption("Legal and Policies", Icons.Filled.Gavel)
            ProfileOption("Help & Feedback", Icons.Filled.Help)
            ProfileOption("About Us", Icons.Filled.Info)

            Spacer(modifier = Modifier.height(10.dp))

            // Logout Button
            Button(
                onClick = {
                    auth.signOut()
                    navigationManager.navigateToLogin()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BCD4))
            ) {
                Text("Log Out", color = Color.White, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))

            // Bottom Navigation Bar
            BottomNavigationBar(navigationManager,Modifier )
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
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

// Profile Option Item
@Composable
fun ProfileOption(label: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { /* Handle Click */ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = label, tint = Color.White, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = label, fontSize = 14.sp, color = Color.White)
    }
}




































































//fun ProfileScreen(auth: FirebaseAuth, navigationManager: NavigationManager) {
//    val currentUser = auth.currentUser
//    val userName = currentUser?.displayName ?: "Guest"
//    val userEmail = currentUser?.email ?: "No email"
//
//    Box(
//        modifier = Modifier.fillMaxSize().background(Color(0xFF141622)),
//        contentAlignment = Alignment.TopCenter
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Spacer(modifier = Modifier.height(20.dp))
//
//            // Profile Info
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Image(
//                    painter = painterResource(id = R.drawable.profile_placeholder),
//                    contentDescription = "Profile Image",
//                    modifier = Modifier.size(80.dp)
//                )
//                Spacer(modifier = Modifier.width(12.dp))
//                Column {
//                    Text(userName, fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)
//                    Text(userEmail, fontSize = 14.sp, color = Color.Gray)
//                }
//            }
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            // Logout Button
//            Button(
//                onClick = {
//                    auth.signOut()
//                    navigationManager.navigateToLogin() // Navigate back to login after logout
//                },
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
//            ) {
//                Text("Logout", color = Color.White)
//            }
//        }
//    }
//}

//@Preview
//@Composable
//fun ProfileScreenpreview(){
//    ProfileScreen(navigationManager = NavigationManager(rememberNavController()),
//        auth = FirebaseAuth.getInstance())
//}