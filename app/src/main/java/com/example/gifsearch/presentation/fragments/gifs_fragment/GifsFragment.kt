package com.example.gifsearch.presentation.fragments.gifs_fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.flatMap
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gifsearch.R
import com.example.gifsearch.databinding.FragmentGifsBinding
import com.example.gifsearch.domain.model.Gif
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GifsFragment:Fragment(), GifsAdapter.OnGifClickListener {

    private lateinit var binding:FragmentGifsBinding
    private val viewmodel by viewModels<GifsViewModel>()
    private lateinit var gifsAdapter: GifsAdapter
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentGifsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeGifs()
        listenAdapter()
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.serch_menu, menu)
                val searchItem=menu.findItem(R.id.search)
                val searchView=searchItem.actionView as SearchView

                searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let{
                            viewmodel.searchGifs(it)
                            binding.recyclerView.scrollToPosition(0)
                            searchView.clearFocus()
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return true
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun listenAdapter() {
        gifsAdapter.addLoadStateListener {loadState->
            binding.apply{
                progressBar.isVisible=loadState.source.refresh is LoadState.Loading
                txtErrorOrEmpty.isVisible=gifsAdapter.itemCount<1
                //recyclerView.isVisible=loadState.source.refresh is LoadState.NotLoading
//                buttonRetry.isVisible=loadState.source.refresh is LoadState.Error
                ///if(loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && gifsAdapter.itemCount<1)
                //    recyclerView.isVisible=false
//                    textViewEmpty.isVisible=true
//                }else{
//                    textViewEmpty.isVisible=false
//                }
            }
        }
    }

    private fun observeGifs() {
        viewmodel.gifs.observe(viewLifecycleOwner){
            gifsAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            binding.recyclerView.isVisible=true
            //binding.txtErrorOrEmpty.isVisible=gifsAdapter.snapshot().isEmpty()
        }
    }

    private fun setupRecyclerView() {
        gifsAdapter= GifsAdapter(this)
        binding.recyclerView.apply {
            adapter=gifsAdapter
            gridLayoutManager=GridLayoutManager(requireContext(), 2)
            layoutManager=gridLayoutManager
            setHasFixedSize(true)
        }
    }

    override fun onGifClickListener(gif: Gif) {
        val action=GifsFragmentDirections.actionGifsFragmentToGifDetailsFragment(gif)
        findNavController().navigate(action)
    }

}