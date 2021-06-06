package br.com.uniptcc.brasilqueimadas.fragment.start.splash.di

import android.app.Application
import br.com.uniptcc.brasilqueimadas.fragment.start.splash.SplashViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @Provides
    fun provideSplashViewModelFactory(application: Application) :SplashViewModelFactory {
        return SplashViewModelFactory(application)
    }


}