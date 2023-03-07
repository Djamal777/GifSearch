package com.example.gifsearch.domain.repositories

import com.example.gifsearch.data.remote.dto.SearchResponse
import com.example.gifsearch.data.remote.dto.TrendingResponse

interface GifSearchRepository {

    suspend fun getTrendingGifs(offset:Int):TrendingResponse

    suspend fun getSearchGifs(offset:Int,q:String):SearchResponse

}