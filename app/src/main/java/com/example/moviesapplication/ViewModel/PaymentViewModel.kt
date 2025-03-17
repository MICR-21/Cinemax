package com.example.moviesapplication.ViewModel

import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.interfaces.MpesaRetrofitInstance
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.example.moviesapplication.interfaces.StkPushRequest

class PaymentViewModel : ViewModel() {

    private val consumerKey = ""
    private val consumerSecret = ""
    private val passKey = ""
    private val shortCode = "174379"
    private val callBackUrl = ""

    fun getBasicAuth(): String {
        val credentials = "$consumerKey:$consumerSecret"
        return "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
    }

    fun fetchMpesaToken(onTokenReceived: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val authHeader = getBasicAuth()
                val tokenResponse = MpesaRetrofitInstance.api.getAccessToken(authHeader)
                Log.d("MpesaToken", "Access Token: ${tokenResponse.access_token}")

                // Pass token back to function
                onTokenReceived(tokenResponse.access_token)
            } catch (e: Exception) {
                Log.e("MpesaError", "Error fetching token", e)
            }
        }
    }

    fun processMpesaPayment(PhoneNumber: String, amount: String) {
        fetchMpesaToken { accessToken ->
            viewModelScope.launch {
                try {
                    val timestamp = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
                        .format(Date())

                    val password = Base64.encodeToString(
                        "$shortCode$passKey$timestamp".toByteArray(),
                        Base64.NO_WRAP
                    )
                    Log.d("MpesaPassword", "Generated Password: $password")

                    val stkPushRequest = StkPushRequest(
                        BusinessShortCode = shortCode,
                        Password = password,
                        Timestamp = timestamp,
                        TransactionType = "CustomerPayBillOnline",
                        Amount = amount,
                        PartyA = PhoneNumber,
                        PartyB = shortCode,
                        PhoneNumber = PhoneNumber,
                        CallBackURL = callBackUrl,
                        AccountReference = "Movie Payment",
                        TransactionDesc = "Payment for movie"
                    )


                    val response = MpesaRetrofitInstance.api.stkPush("Bearer $accessToken", stkPushRequest)

                    Log.d("Mpesa", "STK Push Response: ${response.CustomerMessage}")
                } catch (e: Exception) {
                    Log.e("Mpesa", "Error processing payment", e)
                }
            }
        }
    }
}
