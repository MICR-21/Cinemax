package com.example.moviesapp.ui.screens

import NavigationManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


@Composable
fun PaymentScreen(viewModel: PaymentViewModel, navigationManager: NavigationManager) {

    //Card
    var cardNumber by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var selectedMethod by remember { mutableStateOf("")  }

    //Mpesa
    var phoneNumber by remember { mutableStateOf("") }
    var payAmount by remember { mutableStateOf("") }

    //Paypal

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F1D2B))

            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
                .clickable { navigationManager.goBack() }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
            Spacer(modifier = Modifier.height(20.dp))

            // Reset Password Title
            Text(
                text = "Payment Method",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )


        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            PaymentOption("Card", selectedMethod) { selectedMethod = "Card" }
            PaymentOption("PayPal", selectedMethod) { selectedMethod = "PayPal" }
            PaymentOption("M-Pesa", selectedMethod) { selectedMethod = "M-Pesa" }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Card
        if (selectedMethod == "Card") {
            PaymentInputField("Card Number", cardNumber) { cardNumber = it }
            PaymentInputField("Expiry Date (MM/YY)", expiryDate) { expiryDate = it }
            PaymentInputField("CVV", cvv) { cvv = it }
            PaymentInputField("Pay Amount", payAmount) { payAmount = it }
        }
        //M-Pesa
        if (selectedMethod == "M-Pesa"){
            PaymentInputField("Enter Phone Number", phoneNumber) { phoneNumber = it }
            PaymentInputField("Pay Amount", payAmount) { payAmount = it }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { viewModel.processPayment(selectedMethod, cardNumber, expiryDate, cvv) },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF217FAB)),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Purchase Now", fontSize = 18.sp, color = Color.White)
        }
    }
}

@Composable
fun PaymentOption(name: String, selected: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors((Color(0xFF8C62D0)))
    ) {
        Text(name, modifier = Modifier.padding(16.dp), fontSize = 16.sp, color = Color.White)
    }
}

@Composable
fun PaymentInputField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(label, color = Color.Gray) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors().copy(
            cursorColor = Color.Black,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        )
    )
    Spacer(modifier = Modifier.height(8.dp))
}

class PaymentViewModel : ViewModel() {
    fun processPayment(method: String, cardNumber: String, expiryDate: String, cvv: String) {
        viewModelScope.launch {
            // Simulate payment processing
        }
    }
}
