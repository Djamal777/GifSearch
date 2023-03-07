package com.example.gifsearch.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Onload(
    @SerializedName("url")
    val url: String
)