package com.example.biir.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.biir.data.network.models.response.BeerListDetailResponseModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val context: Application,
    private val mainRepository: MainRepository
) : ViewModel() {

    private val beerListLiveData = MutableLiveData<ArrayList<BeerListDetailResponseModel>>()
    private val beerItemLiveData = MutableLiveData<BeerListDetailResponseModel>()

    fun apiData(): Flow<PagingData<BeerListDetailResponseModel>> {
        return mainRepository.getBeerList().cachedIn(viewModelScope)
    }

    fun updateBeerListLiveData(list: ArrayList<BeerListDetailResponseModel>) {
        beerListLiveData.value = list
    }

    fun prepDataForDetailPage(pos: Int) {
        if(beerListLiveData.value != null) {
            beerItemLiveData.value = beerListLiveData.value!![pos]
        }
    }

    fun beerItemLiveDataListener(): MutableLiveData<BeerListDetailResponseModel> {
        return beerItemLiveData
    }
}