package com.example.gifsearch.data.remote.dto


import com.google.gson.annotations.SerializedName

data class PreviewWebp(
    @SerializedName("height")
    val height: String,
    @SerializedName("size")
    val size: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: String
)