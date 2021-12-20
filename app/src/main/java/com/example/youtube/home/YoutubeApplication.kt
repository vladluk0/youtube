package com.example.youtube.home

import android.app.Application
import android.content.Context
import com.example.youtube.inject.AppComponent
import com.example.youtube.inject.DaggerAppComponent

class YoutubeApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
            is YoutubeApplication -> appComponent
            else -> applicationContext.appComponent
        }
