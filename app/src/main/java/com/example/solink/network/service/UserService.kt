package com.example.solink.network.service

import com.example.solink.network.data.PhotoResponse
import com.example.solink.network.data.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): Response<UserResponse>
}