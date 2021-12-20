package com.example.youtube.data.local.repositories

import com.example.youtube.data.remote.YoutubeService
import javax.inject.Inject

class MostPopularVideosRepository @Inject constructor(
    val youtubeService: YoutubeService
) {
    suspend fun getVideoList() = youtubeService.getVideosList()
}