package com.example.solink.repository

import com.example.solink.network.SLService
import com.example.solink.network.data.UserResponse
import javax.inject.Inject

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val code: Int, val message: String?) : ApiResult<Nothing>()
}

class UserRepository @Inject constructor(
    private val apiService: SLService
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

