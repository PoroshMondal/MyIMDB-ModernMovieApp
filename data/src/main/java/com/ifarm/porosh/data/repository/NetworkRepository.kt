package com.ifarm.porosh.data.repository

import com.ifarm.porosh.data.remote.RetrofitInstance
import com.ifarm.porosh.data.remote.endUrl
import com.ifarm.porosh.domain.models.MovieListModel
import retrofit2.Response

class NetworkRepository {

    suspend fun fetchMovieList(): Response<MovieListModel> {
        return RetrofitInstance.movieServiceApi.fetchMovieList(endUrl = endUrl)
    }

}