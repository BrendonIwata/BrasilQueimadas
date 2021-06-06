package br.com.uniptcc.brasilqueimadas.di

import br.com.uniptcc.brasilqueimadas.core.network.rest.apis.inpa.ApiRest
import br.com.uniptcc.brasilqueimadas.core.network.rest.apis.inpa.ApiService
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideApiRest(): ApiRest {
        return ApiService.apiRest
    }
}