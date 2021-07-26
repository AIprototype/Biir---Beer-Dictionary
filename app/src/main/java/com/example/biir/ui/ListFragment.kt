package com.example.biir.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.biir.R
import com.example.biir.databinding.FragmentListBinding
import com.example.biir.di.ViewModelFactory
import com.example.biir.utils.ItemClickListener
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListFragment : Fragment(), ItemClickListener {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentListBinding
    private lateinit var beerListAdapter: BeerListAdapter
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(MainViewModel::class.java)
        binding.viewModel = mainViewModel
        setUi()
        setObservers()

        //Required to update with live data
        binding.lifecycleOwner = this
    }

    private fun setObservers() {
        lifecycleScope.launch {
            mainViewModel.apiData().collect {
                it.let {
                    beerListAdapter.submitData(it)
                }
            }
        }
    }

    private fun setUi() {
        gridLayoutManager = GridLayoutManager(requireActivity(), 2)
        beerListAdapter = BeerListAdapter(BeerItemDiffCallBack(), this)
        binding.root.beer_list_rv.layoutManager = gridLayoutManager
        binding.root.beer_list_rv.adapter = beerListAdapter

        beerListAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Loading -> {
                    binding.root.main_progress_bar.visibility = View.VISIBLE
                    binding.root.beer_list_rv.visibility = View.GONE
                }
                is LoadState.NotLoading -> {
                    binding.root.main_progress_bar.visibility = View.GONE
                    binding.root.beer_list_rv.visibility = View.VISIBLE
                }
                is LoadState.Error -> {
                    binding.root.main_progress_bar.visibility = View.GONE
                    binding.root.beer_list_rv.visibility = View.GONE
                }
            }
        }
    }

    override fun onClick(view: View?, position: Int, isLongClick: Boolean) {
        Toast.makeText(requireActivity(), "clicked $position", Toast.LENGTH_SHORT).show()
    }
}