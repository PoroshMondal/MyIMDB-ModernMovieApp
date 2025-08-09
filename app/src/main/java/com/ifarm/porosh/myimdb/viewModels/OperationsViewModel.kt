package com.ifarm.porosh.myimdb.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ifarm.porosh.data.local.db.entities.Movies

class OperationsViewModel: ViewModel() {

    private val movieItem: MutableLiveData<Movies> = MutableLiveData<Movies>()

    /*fun getMoviesData(): Movies {
        return movieItem.getValue()
    }*/

    fun setMoviesData(movies: Movies) {
        this.movieItem.value = movies
    }

}