package com.example.moviesapplication.screens

import NavigationManager
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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VerificationScreen(navigationManager: NavigationManager, email: String) {
    var otpCode by remember { mutableStateOf(List(4) { "" }) }
    val focusRequesters = List(4) { FocusRequester() }

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
                    .clickable { navigationManager.goBack() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Title
            Text(
                text = "Verifying Your Account",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Subtitle
            Text(
                text = "We have just sent you a 4-digit code via your email $email",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // OTP Input Fields
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                otpCode.forEachIndexed { index, value ->
                    OutlinedTextField(
                        value = value,
                        onValueChange = { newValue ->
                            if (newValue.length <= 1) {
                                otpCode = otpCode.toMutableList().also { it[index] = newValue }
                                if (newValue.isNotEmpty() && index < 3) {
                                    focusRequesters[index + 1].requestFocus()
                                }
                            }
                        },
                        modifier = Modifier
                            .size(50.dp)
                            .focusRequester(focusRequesters[index])
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF1E1C27)),
                        textStyle = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFF1E1C27),
                            unfocusedContainerColor = Color(0xFF1E1C27),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Continue Button
            Button(
                onClick = { /* Handle Verification */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(
                    text = "Continue",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Resend Code
            Row {
                Text(
                    text = "Didn't receive code?",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Resend",
                    color = Color(0xFF00C2FF),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { /* Handle Resend */ }
                )
            }
        }
    }
}


