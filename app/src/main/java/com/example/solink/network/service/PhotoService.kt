package com.example.solink.network.service

import com.example.solink.network.data.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PhotoService {

    @GET("v1/curated")
    @Headers("Authorization: FyawVqgusyrCd8HvbeY1OpuPTp4fn0tcvaPvirjDMN1ua3uHDLM95Ikg")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<PhotoResponse>
}