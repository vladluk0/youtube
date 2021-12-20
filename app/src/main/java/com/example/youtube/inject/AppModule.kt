package com.example.youtube.inject

import com.example.youtube.home.main.MainActivity
import dagger.Component
import dagger.Module

@Component(modules = [AppModule::class, AppBindsModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)

    val multiViewModelFactory: MultiViewModelFactory

}

@Module(includes = [NetworkModule::class])
class AppModule {
}