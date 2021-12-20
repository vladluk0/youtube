package com.example.youtube.data.local.repositories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class VideosViewModel @Inject constructor(
    val repository: MostPopularVideosRepository
) : ViewModel() {

    fun show() {
        viewModelScope.launch {
            Log.d("tag", "${repository.getVideoList()}")
        }
    }
}