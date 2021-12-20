package com.example.youtube.data.remote.youtube_responsse

data class Item(
    val contentDetails: ContentDetails,
    val etag: String,
    val id: String,
    val kind: String,
    val snippet: Snippet,
    val statistics: Statistics
)