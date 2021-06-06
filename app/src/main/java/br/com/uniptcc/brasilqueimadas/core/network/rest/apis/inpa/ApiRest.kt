package br.com.uniptcc.brasilqueimadas.core.network.rest.apis.inpa

import br.com.uniptcc.brasilqueimadas.core.network.response.BuscarFocosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRest {

    @GET("focos")
    suspend fun buscarFocos (@Query("pais_id") idPais : Long) : Response<List<BuscarFocosResponse>>

    @GET("focos")
    suspend fun buscarFocos (@Query("pais_id") idPais : Long,
                             @Query("estado_id") idEstado : Long) : Response<List<BuscarFocosResponse>>

}