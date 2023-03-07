package com.example.gifsearch.domain.paging_sources

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gifsearch.domain.model.Gif
import com.example.gifsearch.domain.repositories.GifSearchRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class GifsSearchPagingSource @AssistedInject constructor(
    private val repository: GifSearchRepository,
    @Assisted val q:String
) : PagingSource<Int, Gif>() {
    override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, Gif> {
        return try {
            val nextPageNumber = params.key ?: 0
            Log.d("TAG", "loadingData: $nextPageNumber")
            val response = repository.getSearchGifs(offset = nextPageNumber * 25, q)
            Log.d("TAG", "loadingData: ${response.data.joinToString()}")
            PagingSource.LoadResult.Page(
                data = response.data.map {
                    it.toGif()
                },
                prevKey = if(nextPageNumber==0) null else nextPageNumber-1,
                nextKey = if (nextPageNumber * 25 > response.pagination.totalCount) null else (response.pagination.offset / 25) +1
            )
        } catch (e: Exception) {
            PagingSource.LoadResult.Error(e)
        }
    }
}