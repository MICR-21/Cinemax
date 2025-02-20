package com.example.moviesapplication.screens

import NavigationManager
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ResetPasswordScreen(navigationManager: NavigationManager) {
    var email by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141321)) // Dark background
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Back Button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .clickable {
                        navigationManager.navigateToLogin()
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Reset Password Title
            Text(
                text = "Reset Password",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Subtitle
            Text(
                text = "Recover your account password",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Email Input
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Email Address",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF1E1C27)),
                    textStyle = TextStyle(color = Color.White),
                    placeholder = {
                        Text("Enter your email", color = Color.Gray)
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF1E1C27),
                        unfocusedContainerColor = Color(0xFF1E1C27),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Next Button
            Button(
                onClick = {
                        navigationManager.navigateToOTP(email)
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

