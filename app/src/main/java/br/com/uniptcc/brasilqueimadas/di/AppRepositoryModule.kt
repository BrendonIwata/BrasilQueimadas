package br.com.uniptcc.brasilqueimadas.di

import br.com.uniptcc.brasilqueimadas.core.model.repository.InpaRepository
import br.com.uniptcc.brasilqueimadas.core.network.rest.apis.inpa.ApiRest
import dagger.Module
import dagger.Provides

@Module
class AppRepositoryModule {

    @Provides
    fun provideInpaRepository (apiRest: ApiRest): InpaRepository {
        return InpaRepository(apiRest)
    }
}