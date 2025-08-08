package com.ifarm.porosh.data.di

import com.ifarm.porosh.data.remote.apiServices.MovieServicesApi
import com.ifarm.porosh.data.remote.base_url
import com.ifarm.porosh.data.remote.moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    fun provideMovieServiceApi(retrofit: Retrofit): MovieServicesApi =
        retrofit.create(MovieServicesApi::class.java)

}