package com.example.youtube.data.remote

import com.example.youtube.data.remote.youtube_responsse.VideoData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface YoutubeService {

    @GET("videos?part=snippet%2CcontentDetails%2Cstatistics&chart=mostPopular&regionCode=US")
    suspend fun getVideosList() : VideoData
}

