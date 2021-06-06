package br.com.uniptcc.brasilqueimadas.fragment.home.di

import android.app.Application
import br.com.uniptcc.brasilqueimadas.core.model.repository.InpaRepository
import br.com.uniptcc.brasilqueimadas.fragment.home.MainMenuViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainMenuModule {

    @Provides
    fun providesMainMenuViewModelFactory(
        application: Application,
        inpaRepository: InpaRepository
    ): MainMenuViewModelFactory {
        return MainMenuViewModelFactory(application, inpaRepository)
    }
}