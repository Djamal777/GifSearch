package com.example.gifsearch.data.remote.dto


import com.example.gifsearch.domain.model.Gif
import com.google.gson.annotations.SerializedName
import java.util.*

data class DataSearch(
    @SerializedName("analytics")
    val analytics: Analytics,
    @SerializedName("analytics_response_payload")
    val analyticsResponsePayload: String,
    @SerializedName("bitly_gif_url")
    val bitlyGifUrl: String,
    @SerializedName("bitly_url")
    val bitlyUrl: String,
    @SerializedName("content_url")
    val contentUrl: String,
    @SerializedName("embed_url")
    val embedUrl: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: ImagesX,
    @SerializedName("import_datetime")
    val importDatetime: String,
    @SerializedName("is_sticker")
    val isSticker: Int,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("source_post_url")
    val sourcePostUrl: String,
    @SerializedName("source_tld")
    val sourceTld: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("trending_datetime")
    val trendingDatetime: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("username")
    val username: String
){
    fun toGif(): Gif {
        val calendar= Calendar.getInstance()
        calendar.set(Calendar.YEAR, importDatetime.substring(0,4).toInt())
        calendar.set(Calendar.MONTH, importDatetime.substring(5,7).toInt())
        calendar.set(Calendar.DAY_OF_MONTH, importDatetime.substring(8,10).toInt())
        return Gif(
            id=id,
            title=title,
            username=username,
            source=source,
            time="${calendar.get(Calendar.DAY_OF_MONTH)}" +
                    " ${calendar.getDisplayName(Calendar.MONTH, Calendar.LONG_STANDALONE, Locale.getDefault())}" +
                    " ${importDatetime.substring(11,16)}",
            gifImage = images.original.url
        )
    }
}