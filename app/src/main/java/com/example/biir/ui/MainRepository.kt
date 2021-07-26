package com.example.biir.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.biir.data.network.ApiInterface
import com.example.biir.data.network.models.response.BeerListDetailResponseModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {
    fun getBeerList(): Flow<PagingData<BeerListDetailResponseModel>> = Pager(
        config = PagingConfig(25,5,true),
        pagingSourceFactory ={
            BeerListPagingSource(apiInterface)
        }
    ).flow
}