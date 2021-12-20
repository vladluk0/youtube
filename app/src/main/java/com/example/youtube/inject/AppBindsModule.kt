package com.example.youtube.inject

import androidx.lifecycle.ViewModel
import com.example.youtube.data.local.repositories.VideosViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
interface AppBindsModule {

    @Binds
    @[IntoMap ViewModelKey(VideosViewModel::class)]
    fun provideVideosViewModel(videosViewModel: VideosViewModel): ViewModel
}


