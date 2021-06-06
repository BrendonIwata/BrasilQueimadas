package br.com.uniptcc.brasilqueimadas.fragment.start.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.uniptcc.brasilqueimadas.core.util.Constants
import java.lang.IllegalArgumentException
import javax.inject.Inject

class SplashViewModel(application: Application) : AndroidViewModel(application) {

}

class SplashViewModelFactory @Inject constructor(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(application) as T
        }
        throw IllegalArgumentException(Constants.UNKNOWN_VIEW_MODEL_CLASS)
    }

}