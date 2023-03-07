package com.example.gifsearch.presentation.fragments.gifs_fragment

import android.content.Context
import android.media.AudioRecord.MetricsConstants.SOURCE
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.example.gifsearch.R
import com.example.gifsearch.databinding.GifItemBinding
import com.example.gifsearch.domain.model.Gif


class GifsAdapter(
    private val listener: OnGifClickListener
) : PagingDataAdapter<Gif, GifsAdapter.GifViewHolder>(DiffObj) {
    object DiffObj : DiffUtil.ItemCallback<Gif>() {
        override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
            return oldItem == newItem
        }
    }

    inner class GifViewHolder(private val binding: GifItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init{
            binding.root.setOnClickListener{
                val position=bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onGifClickListener(getItem(position)!!)
                }
            }
        }
        fun bind(item: Gif?) {
            Glide.with(binding.root.context)
                .asGif()
                .load(item?.gifImage)
                .placeholder(R.drawable.ic_baseline_gif_24)
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(binding.gifImage)
        }
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        return GifViewHolder(GifItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    interface OnGifClickListener {
        fun onGifClickListener(gif: Gif)
    }
}