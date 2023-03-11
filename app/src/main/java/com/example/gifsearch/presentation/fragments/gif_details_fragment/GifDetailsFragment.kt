package com.example.gifsearch.presentation.fragments.gif_details_fragment

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.gifsearch.R
import com.example.gifsearch.data.downloader.AndroidDownloader
import com.example.gifsearch.databinding.FragmentGifDetailsBinding
import com.example.gifsearch.domain.downloader.Downloader
import dagger.hilt.android.AndroidEntryPoint

@RequiresApi(Build.VERSION_CODES.M)
@AndroidEntryPoint
class GifDetailsFragment : Fragment() {

    private lateinit var binding: FragmentGifDetailsBinding
    private val viewModel by viewModels<GifDetailsViewModel>()
    private val downloader: Downloader by lazy {
        AndroidDownloader(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGifDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachData()
        createMenu()
    }

    private fun createMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.gif_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.download -> {
                        downloadGif()
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun downloadGif() {
        viewModel.gif?.let {
            Toast.makeText(requireContext(), "Загрузка гифки...", Toast.LENGTH_SHORT).show()
            downloader.downloadFile(it.gifImage, it.title)
        }
    }

    private fun attachData() {
        binding.apply {
            viewModel.gif?.let {
                Glide.with(requireContext())
                    .asGif()
                    .load(it.gifImage)
                    .placeholder(R.drawable.ic_baseline_gif_24)
                    .transition(withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(binding.gifImage)
                author.text = "Имя пользователя: ${it.username}"
                dateReleased.text = "Время публикации: ${it.time}"
                title.text = it.title
                if (it.source.isNullOrEmpty()) {
                    source.visibility = View.GONE
                } else source.text = "Источник: ${it.source}"
            }
        }
    }

}