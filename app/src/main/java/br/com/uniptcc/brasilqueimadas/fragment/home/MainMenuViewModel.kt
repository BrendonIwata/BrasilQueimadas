package br.com.uniptcc.brasilqueimadas.fragment.home

import android.app.Application
import androidx.lifecycle.*
import br.com.uniptcc.brasilqueimadas.core.model.repository.InpaRepository
import br.com.uniptcc.brasilqueimadas.core.network.response.BuscarFocosResponse
import br.com.uniptcc.brasilqueimadas.core.util.Constants
import br.com.uniptcc.brasilqueimadas.fragment.basic.BasicViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

class MainMenuViewModel(
    application: Application,
    val inpaRepository: InpaRepository
) : BasicViewModel(application) {

    private var _focosAmazonas = MutableLiveData<List<BuscarFocosResponse>?>()
    val focosAmazonas: LiveData<List<BuscarFocosResponse>?>
        get() = _focosAmazonas

    private var _mapaCarregou = MutableLiveData<Boolean?>()
    val mapaCarregou: LiveData<Boolean?>
        get() = _mapaCarregou

    private var _focosVazios = MutableLiveData<Boolean?>()
    val focosVazios: LiveData<Boolean?>
        get() = _focosVazios

    companion object {
        const val BRASIL = 33L
        const val AMAZONAS = 13L
    }

    fun buscarFocosDeIncendio () {
        coroutinesScope.launch {
            val focosAmazonasResponse = inpaRepository.buscarFocosPaisEstadoDeUmPais(BRASIL, AMAZONAS)

            if (focosAmazonasResponse.isEmpty()) {
                _focosVazios.value = true
            } else {
                _focosAmazonas.value = focosAmazonasResponse
            }

        }
    }

    fun setarStatusDoCarregamentoDoMapa(sucesso : Boolean) {
        _mapaCarregou.value = sucesso
    }

    override fun clearDataBinding() {

    }


}

class MainMenuViewModelFactory @Inject constructor(
    private val application: Application,
    private val inpaRepository: InpaRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainMenuViewModel::class.java)) {
            return MainMenuViewModel(application, inpaRepository) as T
        }

        throw IllegalArgumentException(Constants.UNKNOWN_VIEW_MODEL_CLASS)
    }


}