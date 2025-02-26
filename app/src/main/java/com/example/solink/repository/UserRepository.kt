package com.example.solink.repository

import com.example.solink.network.ApiResult
import com.example.solink.network.data.UserResponse
import com.example.solink.network.service.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: UserService
) {
    suspend fun fetchUserById(userId: Int): ApiResult<UserResponse> {
        return try {
            val response = apiService.getUserById(userId)
            if (response.isSuccessful) {
                response.body()?.let {
                    ApiResult.Success(it)
                } ?: ApiResult.Error(response.code(), "Empty response body")
            } else {
                val errorBody = response.errorBody()?.string()
                ApiResult.Error(response.code(), errorBody ?: "Unknown error")
            }
        } catch (e: Exception) {
            ApiResult.Error(-1, e.localizedMessage ?: "Unknown exception")
        }
    }
}

