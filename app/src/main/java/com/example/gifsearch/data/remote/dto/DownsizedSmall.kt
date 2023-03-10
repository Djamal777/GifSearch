package com.example.gifsearch.data.remote.dto


import com.google.gson.annotations.SerializedName

data class DownsizedSmall(
    @SerializedName("height")
    val height: String,
    @SerializedName("mp4")
    val mp4: String,
    @SerializedName("mp4_size")
    val mp4Size: String,
    @SerializedName("width")
    val width: String
)