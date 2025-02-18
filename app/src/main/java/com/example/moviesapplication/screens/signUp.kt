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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moviesapplication.R


@Composable
fun SignUpScreen(navigationManager: NavigationManager) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141622)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Use Image instead of Icon for a drawable resource
            Image(
                painter = painterResource(id = R.drawable.popcornapp),
                contentDescription = "App Icon",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(46.dp))

            Text("CINEMAX", fontSize = 34.sp, fontWeight = FontWeight.Bold, color = Color.White)

            Spacer(modifier = Modifier.height(20.dp))

            Text("Enter your registered", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Gray)

            Text ("Phone Number to Sign Up", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Gray)

            Spacer(modifier = Modifier.height(20.dp))

            var text by remember { mutableStateOf("") }

            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Enter Phone Number", color = Color.Gray, fontSize = 14.sp, textAlign = TextAlign.Right, fontFamily = FontFamily.SansSerif ) },
                shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            Spacer(modifier = Modifier.height(26.dp))

            Button(
                onClick = { navigationManager.navigateToLogin()},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E5FF)),
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text("Sign Up", fontSize = 20.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(26.dp))

            // Use Text instead of ClickableText for simplicity
            Text(
                AnnotatedString("I already have an account? Login"),
                color = Color(0xFF00E5FF),
                fontSize = 18.sp,
                modifier = Modifier.clickable { navigationManager.navigateToLogin() }
            )

            Spacer(modifier = Modifier.height(26.dp))
            Text("Or Sign up with", color = Color.Gray, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(26.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                IconButton(onClick = { /* Google Sign-in */ }) {
                    Image(painter = painterResource(id = R.drawable.google), contentDescription = "Google", modifier = Modifier.size(90.dp))
                }
                IconButton(onClick = { /* Apple Sign-in */ }) {
                    Image(painter = painterResource(id = R.drawable.apple), contentDescription = "Apple", modifier = Modifier.size(90.dp))
                }
                IconButton(onClick = { /* Facebook Sign-in */ }) {
                    Image(painter = painterResource(id = R.drawable.facebook), contentDescription = "Facebook", modifier = Modifier.size(90.dp))
                }
            }
        }
    }
}



@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(navigationManager = NavigationManager(rememberNavController()))
}
//Text(
//buildAnnotatedString{
//    withStyle(style = SpanStyle(color = Color.Gray, fontSize = 18.sp)) {
//        append("I already have an account? ")
//    }
//    withStyle(style = SpanStyle(color = Color(0xFF00E5FF), fontSize = 18.sp)) {
//        append("Login")
//    }
//}
//)