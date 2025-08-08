package com.ifarm.porosh.myimdb.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ifarm.porosh.data.remote.apiResponse.ApiResponse
import com.ifarm.porosh.data.repository.NetworkRepository
import com.ifarm.porosh.domain.models.MovieListModel
import com.ifarm.porosh.myimdb.utilities.IMDBConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(val networkRepository: NetworkRepository) : ViewModel() {

    fun fetchMovieList(): LiveData<ApiResponse<MovieListModel>> {
        return liveData(Dispatchers.IO) {
            emit(ApiResponse.Loading)
            try {
                val response = networkRepository.fetchMovieList()
                if (response.isSuccessful && response.body()!=null){
                    Log.i("network_module","network call - successful: ${response.body().toString()}")
                    response.body()?.let {
                        if (it.movies.isNotEmpty()){
                            emit(ApiResponse.Success(response.body()) as ApiResponse<MovieListModel>)
                        }else{
                            emit(ApiResponse.Error(IMDBConstants.NETWORK_NO_DATA_MSG))
                        }
                    }

                }else{
                    Log.i("network_module","Movie List - network call - unsuccessful Error: ${response.message()}")
                    emit(ApiResponse.Error(" ${IMDBConstants.NETWORK_FAILED_FETCH_MSG} Error: ${response.message()}"))
                }
            }catch (e: kotlin.Exception){
                Log.i("network_module","Movie List - network call - exception: $e")
                emit(ApiResponse.Error(IMDBConstants.Constants.NETWORK_FAILED_MSG))
            }
        }
    }

}