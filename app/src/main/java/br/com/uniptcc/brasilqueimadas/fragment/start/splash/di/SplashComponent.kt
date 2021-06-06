package br.com.uniptcc.brasilqueimadas.fragment.start.splash.di

import br.com.uniptcc.brasilqueimadas.fragment.start.splash.SplashFragment
import dagger.Subcomponent

@Subcomponent(modules = [SplashModule::class])
interface SplashComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): SplashComponent
    }

    fun inject(fragment: SplashFragment)
}