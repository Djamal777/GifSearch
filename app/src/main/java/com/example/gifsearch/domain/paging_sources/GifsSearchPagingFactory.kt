package com.example.gifsearch.domain.paging_sources

import dagger.assisted.AssistedFactory

@AssistedFactory
interface GifsSearchPagingFactory {
    fun create(q:String): GifsSearchPagingSource
}