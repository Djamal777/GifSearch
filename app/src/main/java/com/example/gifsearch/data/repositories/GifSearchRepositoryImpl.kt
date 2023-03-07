package com.example.gifsearch.data.repositories

import com.example.gifsearch.data.remote.GifyApi
import com.example.gifsearch.data.remote.dto.SearchResponse
import com.example.gifsearch.data.remote.dto.TrendingResponse
import com.example.gifsearch.domain.repositories.GifSearchRepository
import javax.inject.Inject

class GifSearchRepositoryImpl @Inject constructor(
    private val api:GifyApi
):GifSearchRepository {
    override suspend fun getTrendingGifs(offset:Int): TrendingResponse {
        return api.getTrendingGifs(offset=offset)
    }

    override suspend fun getSearchGifs(offset:Int, q: String): SearchResponse {
        return api.getSearchGifs(offset=offset, q=q)
    }
}