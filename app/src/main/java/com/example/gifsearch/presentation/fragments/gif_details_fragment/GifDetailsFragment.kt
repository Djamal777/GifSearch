package com.example.gifsearch.presentation.fragments.gif_details_fragment

import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.gifsearch.R
import com.example.gifsearch.databinding.FragmentGifDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GifDetailsFragment:Fragment() {

    private lateinit var binding: FragmentGifDetailsBinding
    private val viewModel by viewModels<GifDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentGifDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel.gif?.let{
                Glide.with(requireContext())
                    .asGif()
                    .load(it.gifImage)
                    .placeholder(R.drawable.ic_baseline_gif_24)
                    .transition(withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(binding.gifImage)
                author.text="Имя пользователя: ${it.username}"
                dateReleased.text="Время публикации: ${it.time}"
                title.text=it.title
                if(it.source.isNullOrEmpty()){
                    source.visibility=View.GONE
                }else source.text="Источник: ${it.source}"
            }
        }
    }

}