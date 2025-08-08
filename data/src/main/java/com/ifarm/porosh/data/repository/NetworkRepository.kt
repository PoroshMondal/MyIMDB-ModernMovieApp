package com.ifarm.porosh.data.repository

//import com.ifarm.porosh.data.remote.RetrofitInstance
import com.ifarm.porosh.data.remote.apiServices.MovieServicesApi
import com.ifarm.porosh.data.remote.endUrl
import com.ifarm.porosh.domain.models.MovieListModel
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val movieServiceApi: MovieServicesApi
) {
    suspend fun fetchMovieList(): Response<MovieListModel> {
        //return RetrofitInstance.movieServiceApi.fetchMovieList(endUrl = endUrl)
        return movieServiceApi.fetchMovieList(endUrl = endUrl)
    }
}