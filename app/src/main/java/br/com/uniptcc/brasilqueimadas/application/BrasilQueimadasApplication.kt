package br.com.uniptcc.brasilqueimadas.application

import android.app.Application
import android.content.Context
import br.com.uniptcc.brasilqueimadas.di.AppComponent
import br.com.uniptcc.brasilqueimadas.di.DaggerAppComponent

class BrasilQueimadasApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        setContext(this)
    }

    companion object {
        private var sContext: Context? = null
        fun setContext(context: Context) {
            sContext = context
        }

        fun getContext(): Context? {
            return sContext
        }
    }
}