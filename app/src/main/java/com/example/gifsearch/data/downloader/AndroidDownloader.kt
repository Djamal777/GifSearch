package com.example.gifsearch.data.downloader

import android.app.DownloadManager
import android.content.Context
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import com.example.gifsearch.domain.downloader.Downloader
import java.io.File

@RequiresApi(Build.VERSION_CODES.M)
class AndroidDownloader(
    private val context: Context
):Downloader {

    private val downloadManager=context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String, title: String): Long {
        val request=DownloadManager.Request(url.toUri())
            .setMimeType("image/gif")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("$title.gif")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"${File.separator}Gifs${File.separator}DownloadedGifs${File.separator}$title.gif")
        return downloadManager.enqueue(request)
    }
}