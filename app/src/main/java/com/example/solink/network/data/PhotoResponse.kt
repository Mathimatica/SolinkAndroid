package com.example.solink.network.data

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("photos")
    val photos: List<Photo>
)

data class Photo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("photographer")
    val photographer: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("src")
    val src: ImageSources
)

data class ImageSources(
    @SerializedName("original")
    val original: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("small")
    val small: String
)