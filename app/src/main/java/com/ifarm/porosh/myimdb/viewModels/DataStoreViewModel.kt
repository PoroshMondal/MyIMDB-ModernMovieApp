package com.ifarm.porosh.myimdb.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ifarm.porosh.data.repository.local.dataStorePref.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(val dataStoreRepository: DataStoreRepository) : ViewModel() {

    fun setDataStoredInfo(isStored: Boolean){
        viewModelScope.launch {
            dataStoreRepository.setDataStoredInfo(isStored)
        }
    }

    fun getIsDataStored(): LiveData<Boolean> {
        return dataStoreRepository.getIsDataStored().asLiveData()
    }

}