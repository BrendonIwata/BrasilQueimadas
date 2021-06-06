package br.com.uniptcc.brasilqueimadas.core.model.repository

import br.com.uniptcc.brasilqueimadas.core.model.exception.ResponseApiInpaException
import br.com.uniptcc.brasilqueimadas.core.network.response.BuscarFocosResponse
import br.com.uniptcc.brasilqueimadas.core.network.rest.apis.inpa.ApiRest
import javax.inject.Inject

class InpaRepository @Inject constructor(private val apiRest: ApiRest) : BaseRepository() {


    suspend fun buscarFocosPorPais(idPais: Long): List<BuscarFocosResponse> {

        validacoesPreRequest()

        val buscarFocos = apiRest.buscarFocos(idPais)

        if (!buscarFocos.isSuccessful) {
            throw ResponseApiInpaException("Houve um erro ao buscar os focos de incêndio")
        }

        buscarFocos.body()?.let {
            return it
        }

        throw Exception("Houve um erro desconhecido")
    }

    suspend fun buscarFocosPaisEstadoDeUmPais(idPais: Long, idEstado: Long): List<BuscarFocosResponse> {
        validacoesPreRequest()

        val buscarFocosResponse = apiRest.buscarFocos(idPais, idEstado)

        if (!buscarFocosResponse.isSuccessful) {
            throw ResponseApiInpaException("Houve um erro ao buscar os focos de incêndio")
        }

        buscarFocosResponse.body()?.let {
            return it
        }

        throw Exception("Houve um erro desconhecido")
    }
}