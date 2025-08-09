package com.ifarm.porosh.data.remote.apiServices

import com.ifarm.porosh.domain.models.MovieListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface MovieServicesApi {
    @GET
    suspend fun fetchMovieList(@Url endUrl: String): Response<MovieListModel>
}