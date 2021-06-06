package br.com.uniptcc.brasilqueimadas.di

import br.com.uniptcc.brasilqueimadas.fragment.home.di.MainMenuComponent
import br.com.uniptcc.brasilqueimadas.fragment.start.splash.di.SplashComponent
import dagger.Module

@Module(
    subcomponents = [
        SplashComponent::class,
        MainMenuComponent::class
    ]
)
class AppSubcomponentsModule