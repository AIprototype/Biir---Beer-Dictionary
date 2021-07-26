package com.example.biir.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.biir.data.network.ApiInterface
import com.example.biir.data.network.models.response.BeerListDetailResponseModel
import retrofit2.HttpException
import java.io.IOException

const val STARTING_PAGE_ID = 1

class BeerListPagingSource constructor(
    private val apiInterface: ApiInterface
): PagingSource<Int, BeerListDetailResponseModel>() {
    override fun getRefreshKey(state: PagingState<Int, BeerListDetailResponseModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey ?: anchorPage?.nextKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BeerListDetailResponseModel> {
        val position: Int = params.key?: STARTING_PAGE_ID

        return try {
            val data = apiInterface.getBeerData(position, 25)
            LoadResult.Page(
                data = data.body() as List<BeerListDetailResponseModel>,
                prevKey = params.key,
                nextKey = (position + 1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}