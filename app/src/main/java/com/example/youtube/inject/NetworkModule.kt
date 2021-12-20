package com.example.youtube.inject

import com.example.youtube.data.remote.YoutubeService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val originalHttpUrl = chain.request().url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("key", "AIzaSyAcRAgyPV_1miQyvliGP8wdCdm5XxPkd7A").build()
                request.url(url)
                return@addInterceptor chain.proceed(request.build())
            }
            .build()
    }

    //@Singleton
    @Provides
    fun provideYoutubeService(okHttpClient: OkHttpClient): YoutubeService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://youtube.googleapis.com/youtube/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(YoutubeService::class.java)
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Runtime