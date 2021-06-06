package br.com.uniptcc.brasilqueimadas.di

import android.app.Application
import br.com.uniptcc.brasilqueimadas.fragment.home.di.MainMenuComponent
import br.com.uniptcc.brasilqueimadas.fragment.start.splash.di.SplashComponent
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class, AppSubcomponentsModule::class, AppRepositoryModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun splashComponent(): SplashComponent.Factory
    fun mainMenuComponent(): MainMenuComponent.Factory


}