package br.com.uniptcc.brasilqueimadas.fragment.home.di

import br.com.uniptcc.brasilqueimadas.fragment.home.MainMenuFragment
import dagger.Subcomponent

@Subcomponent(modules = [MainMenuModule::class])
interface MainMenuComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainMenuComponent
    }

    fun inject(fragment: MainMenuFragment)
}