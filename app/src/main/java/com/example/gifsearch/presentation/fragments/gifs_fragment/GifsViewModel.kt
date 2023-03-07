package com.example.gifsearch.presentation.fragments.gifs_fragment

import androidx.lifecycle.*
import androidx.paging.*
import com.example.gifsearch.domain.paging_sources.GifsPagingSource
import com.example.gifsearch.domain.paging_sources.GifsSearchPagingFactory
import com.example.gifsearch.domain.paging_sources.GifsSearchPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GifsViewModel @Inject constructor(
    private val gifsPagingSource: GifsPagingSource,
    private val gifsSearchPagingFactory: GifsSearchPagingFactory
) :ViewModel() {

    private val curQuery=MutableLiveData<String>("")

    val gifs = curQuery.switchMap {
        Pager(
            config = PagingConfig(
                25,
                3,
                enablePlaceholders = false
            )
        ){
            if(it.isNullOrEmpty()) gifsPagingSource
            else gifsSearchPagingFactory.create(it)
        }.liveData
    }.cachedIn(viewModelScope)

    fun searchGifs(q:String){
        curQuery.value=q
    }
}