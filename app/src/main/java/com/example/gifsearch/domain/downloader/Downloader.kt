package com.example.gifsearch.domain.downloader

interface Downloader {
    fun downloadFile(url:String, title:String):Long
}