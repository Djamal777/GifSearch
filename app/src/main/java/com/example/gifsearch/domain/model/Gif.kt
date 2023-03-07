package com.example.gifsearch.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gif(
    val id:String,
    val title:String,
    val username:String,
    val source:String,
    val time:String,
    val gifImage:String
):Parcelable
