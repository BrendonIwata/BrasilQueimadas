package br.com.uniptcc.brasilqueimadas.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.uniptcc.brasilqueimadas.R
import br.com.uniptcc.brasilqueimadas.application.BrasilQueimadasApplication
import br.com.uniptcc.brasilqueimadas.core.network.response.BuscarFocosResponse
import br.com.uniptcc.brasilqueimadas.databinding.FragmentMainMenuBinding
import br.com.uniptcc.brasilqueimadas.fragment.home.di.MainMenuComponent
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class MainMenuFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mapa: MapView

    private lateinit var viewModel: MainMenuViewModel
    private lateinit var component: MainMenuComponent

    @Inject
    lateinit var factory: MainMenuViewModelFactory

    private var mGoogleMap: GoogleMap? = null
    var latlng: LatLng? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentMainMenuBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main_menu, container, false)
        binding.lifecycleOwner = this

        val appApplication = (requireNotNull(activity).application as BrasilQueimadasApplication)
        component = appApplication.appComponent.mainMenuComponent().create()
        component.inject(this)

        viewModel = ViewModelProvider(this, factory).get(MainMenuViewModel::class.java)

        val fragment: Fragment = this

        binding.run {
            mapa = mapView

            viewModel.mapaCarregou.observe(viewLifecycleOwner, {
                if (it != null) {
                    if (it) {
                        viewModel.buscarFocosDeIncendio()
                    } else {
                        Toast.makeText(fragment.context, "", Toast.LENGTH_LONG).show()
                    }
                }
            })
            viewModel.focosAmazonas.observe(viewLifecycleOwner, {
                if (it != null) {
                    setarMarkersDosFocosDeIncendio(it)
                }
            })

            viewModel.focosVazios.observe(viewLifecycleOwner, {
                if (it != null) {
                    if (it) {
                        Snackbar.make(binding.root, fragment.getString(R.string.sem_focos_amazonas), Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.OK){}.show()
                    }
                }
            })
        }

        mapa.onCreate(savedInstanceState)
        mapa.getMapAsync(this)

        return binding.root
    }

    override fun onResume() {
        Log.i("info", "executou o onresume do menu principal")
        mapa.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mapa.onDestroy()
        super.onDestroy()
    }

    fun setarMarkersDosFocosDeIncendio(focos: List<BuscarFocosResponse>) {

        val builder = LatLngBounds.Builder()

        focos.forEach {
            latlng = LatLng(
                it.properties.latitude.toDouble(),
                it.properties.longitude.toDouble()
            ) //TODO aqui vão vir as latitudes e longitudes da consulta
            val marker: Marker = mGoogleMap!!.addMarker(
                MarkerOptions().position(latlng).title(it.properties.municipio)
            )

            builder.include(marker.position)
        }

        val bounds: LatLngBounds = builder.build()
        val padding = 0
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)
        mGoogleMap!!.animateCamera(cu)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        // Need to call MapsInitializer before doing any CameraUpdateFactory calls
        // Need to call MapsInitializer before doing any CameraUpdateFactory calls
        try {
            mGoogleMap = googleMap
            MapsInitializer.initialize(requireContext())
            viewModel.setarStatusDoCarregamentoDoMapa(true)
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
            viewModel.setarStatusDoCarregamentoDoMapa(false)
        }
    }
}