package com.example.gifsearch.presentation.fragments.gif_details_fragment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.gifsearch.domain.model.Gif
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GifDetailsViewModel @Inject constructor(
    state:SavedStateHandle
):ViewModel() {
    val gif: Gif?=state["gif"]
}