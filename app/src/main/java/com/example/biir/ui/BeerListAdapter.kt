package com.example.biir.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.biir.data.network.models.response.BeerListDetailResponseModel
import com.example.biir.databinding.BeerListItemBinding
import com.example.biir.utils.ItemClickListener

class BeerListAdapter constructor(
    @NonNull diffCallback: DiffUtil.ItemCallback<BeerListDetailResponseModel>,
    var itemClickListener: ItemClickListener
) : PagingDataAdapter<BeerListDetailResponseModel, RecyclerView.ViewHolder>(diffCallback) {

    @NonNull
    override fun onCreateViewHolder(
        @NonNull parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View
        val beerListBinding = BeerListItemBinding.inflate(inflater, parent, false)
        return BeerViewHolder(beerListBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BeerViewHolder).bind(getItem(position))
    }

    inner class BeerViewHolder(
        private val binding: BeerListItemBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(item: BeerListDetailResponseModel?) {
            binding.beerItem = item
        }

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            itemClickListener.onClick(p0, bindingAdapterPosition, false)
        }
    }
}