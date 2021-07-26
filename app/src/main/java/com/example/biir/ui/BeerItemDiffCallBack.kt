package com.example.biir.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.biir.data.network.models.response.BeerListDetailResponseModel

class BeerItemDiffCallBack : DiffUtil.ItemCallback<BeerListDetailResponseModel>() {
    override fun areItemsTheSame(oldItem: BeerListDetailResponseModel, newItem: BeerListDetailResponseModel): Boolean {
        return oldItem.id.equals(newItem.id)
    }

    override fun areContentsTheSame(oldItem: BeerListDetailResponseModel, newItem: BeerListDetailResponseModel): Boolean {
        return oldItem == newItem
    }
}