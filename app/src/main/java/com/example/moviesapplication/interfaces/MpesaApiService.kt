package com.example.moviesapplication.interfaces

import android.util.Base64
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Headers

interface MpesaApiService {

    @Headers("Content-Type: application/json")
    @GET("oauth/v1/generate?grant_type=client_credentials")
    suspend fun getAccessToken(
        @Header("Authorization") authorization: String
    ): AccessTokenResponse

    @POST("mpesa/stkpush/v1/processrequest")
    suspend fun stkPush(
        @Header("Authorization") auth: String,
        @Body request: StkPushRequest
    ): StkPushResponse
}

// Response data class for access token
data class AccessTokenResponse(
    val access_token: String,
    val expires_in: String
)

// Request data class for STK Push
data class StkPushRequest(
    val BusinessShortCode: String,
    val Password: String,
    val Timestamp: String,
    val TransactionType: String = "CustomerPayBillOnline",
    val Amount: String,
    val PartyA: String,
    val PartyB: String,
    val PhoneNumber: String,
    val CallBackURL: String,
    val AccountReference: String,
    val TransactionDesc: String
)

// Response data class for STK Push
data class StkPushResponse(
    val merchantRequestID: String,
    val checkoutRequestID: String,
    val responseCode: String,
    val responseDescription: String,
    val CustomerMessage: String
)
