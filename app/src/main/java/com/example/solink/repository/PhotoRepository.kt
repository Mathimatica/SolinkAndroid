package com.example.solink.repository

import com.example.solink.network.ApiResult
import com.example.solink.network.data.PhotoResponse
import com.example.solink.network.service.PhotoService
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val apiService: PhotoService
) {
    suspend fun fetchPhotoById(page: Int, perPage:Int): ApiResult<PhotoResponse> {
        return try {
            val response = apiService.getPhotos(page, perPage)
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