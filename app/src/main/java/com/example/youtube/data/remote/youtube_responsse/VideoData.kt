package com.example.youtube.data.remote.youtube_responsse

data class VideoData(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo
)