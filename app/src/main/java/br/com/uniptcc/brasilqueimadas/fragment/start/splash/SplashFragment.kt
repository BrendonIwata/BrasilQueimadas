package br.com.uniptcc.brasilqueimadas.fragment.start.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import br.com.uniptcc.brasilqueimadas.R
import br.com.uniptcc.brasilqueimadas.application.BrasilQueimadasApplication
import br.com.uniptcc.brasilqueimadas.databinding.FragmentSplashBinding
import br.com.uniptcc.brasilqueimadas.fragment.start.splash.di.SplashComponent
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashFragment : Fragment() {


    @Inject lateinit var factory: SplashViewModelFactory
    private lateinit var viewModel: SplashViewModel
    private lateinit var component: SplashComponent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentSplashBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_splash, container, false)
        binding.lifecycleOwner = this

        val appApplication = (requireNotNull(activity).application as BrasilQueimadasApplication)
        component = appApplication.appComponent.splashComponent().create()
        component.inject(this)

        viewModel = ViewModelProvider(this, factory).get(SplashViewModel::class.java)
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Log.i("info", "executou o onresume do splash")
        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(3))
            withContext(Dispatchers.Main) {
                view?.findNavController()?.navigate(R.id.action_splash_to_main_menu_)
            }
        }

    }
}
