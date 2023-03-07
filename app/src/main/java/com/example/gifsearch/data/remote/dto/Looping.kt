package com.example.gifsearch.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Looping(
    @SerializedName("mp4")
    val mp4: String,
    @SerializedName("mp4_size")
    val mp4Size: String
)