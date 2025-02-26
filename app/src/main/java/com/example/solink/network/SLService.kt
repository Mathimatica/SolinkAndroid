package com.example.solink.network

import com.example.solink.network.data.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SLService {
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): Response<UserResponse>
}