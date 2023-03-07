package com.example.gifsearch.data.remote.dto


import com.google.gson.annotations.SerializedName

data class TrendingResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("pagination")
    val pagination: Pagination
)