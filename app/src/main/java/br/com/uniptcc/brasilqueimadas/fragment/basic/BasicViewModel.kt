package br.com.uniptcc.brasilqueimadas.fragment.basic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BasicViewModel(application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    protected val coroutinesScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    abstract fun clearDataBinding ()
}