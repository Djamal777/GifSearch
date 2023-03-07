package com.example.gifsearch.di

import com.example.gifsearch.data.remote.GifyApi
import com.example.gifsearch.data.repositories.GifSearchRepositoryImpl
import com.example.gifsearch.domain.repositories.GifSearchRepository
import com.example.gifsearch.other.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit()=Retrofit.Builder()
        .baseUrl(Const.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun pprovideGifyApi(retrofit: Retrofit):GifyApi=
        retrofit.create(GifyApi::class.java)

    @Provides
    @Singleton
    fun provideGifRepository(api:GifyApi):GifSearchRepository=GifSearchRepositoryImpl(api)
}