package br.com.uniptcc.brasilqueimadas.core.model.repository

import br.com.uniptcc.brasilqueimadas.R
import br.com.uniptcc.brasilqueimadas.application.BrasilQueimadasApplication
import br.com.uniptcc.brasilqueimadas.core.model.exception.NetworkException
import br.com.uniptcc.brasilqueimadas.core.util.NetworkUtil

abstract class BaseRepository {

    fun validacoesPreRequest() {
        val context = BrasilQueimadasApplication.getContext()!!
        if (!NetworkUtil.isConnected(context)) {
            throw NetworkException(context.getString(R.string.without_internet))
        }
    }
}