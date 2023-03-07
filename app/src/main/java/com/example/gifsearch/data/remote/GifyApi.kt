package com.example.gifsearch.data.remote

import com.example.gifsearch.BuildConfig
import com.example.gifsearch.data.remote.dto.SearchResponse
import com.example.gifsearch.data.remote.dto.TrendingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GifyApi {

    @GET("/v1/gifs/trending")
    suspend fun getTrendingGifs(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("limit") limit: Int = 25,
        @Query("offset") offset: Int = 0
    ): TrendingResponse

    @GET("/v1/gifs/search")
    suspend fun getSearchGifs(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("q") q: String,
        @Query("limit") limit: Int = 25,
        @Query("offset") offset: Int = 0,
        @Query("lang") lang: String = "ru"
    ): SearchResponse
}