package com.example.youtube.data.remote.youtube_responsse

data class ContentDetails(
    val caption: String,
    val contentRating: ContentRating,
    val definition: String,
    val dimension: String,
    val duration: String,
    val licensedContent: Boolean,
    val projection: String
)