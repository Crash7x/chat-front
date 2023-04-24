package com.example.weather

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.constants.TransmitNameCityByKey
import com.example.core.flow.observe
import com.example.core.viewmodel.ViewModelFactory
import com.example.weather.adapter.dailyweather.DailyItem
import com.example.weather.adapter.dailyweather.HeaderItem
import com.example.weather.common.WeatherComponentHolder
import com.example.weather.databinding.FragmentDailyWeatherBinding
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import javax.inject.Inject

class DailyWeatherFragment : Fragment() {
    private var _binding: FragmentDailyWeatherBinding? = null
    private val binding get() = _binding ?: throw NullPointerException("Binding is not initialized")

    private val headerAdapter = ItemAdapter<HeaderItem>()
    private val itemAdapter = GenericItemAdapter()
    private val fastAdapter = FastAdapter.with(listOf(headerAdapter, itemAdapter))

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: DailyWeatherViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        WeatherComponentHolder
            .getComponentImpl()
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
            viewModel.getWeatherDataLocation()
        } else if (permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
            viewModel.getWeatherDataLocation()
        } else {
            Toast.makeText(
                requireContext(),
                "Доступ к местоположению не предоставлен",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyWeatherBinding.inflate(inflater, container, false)

        setFragmentResultListener(TransmitNameCityByKey.REQUEST_KEY) { key, bundle ->
            val result = bundle.getString(TransmitNameCityByKey.BUNDLE_KEY)
            viewModel.displayDataWeather(result.toString())
        }

        binding.search.setOnClickListener {
            viewModel.actionToScreenCity()
        }

        getWeatherByLocation()

        binding.recycler.adapter = fastAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.header.observe(
            viewLifecycleOwner.lifecycleScope,
            action = { FastAdapterDiffUtil[headerAdapter] = it.map(::HeaderItem) },
            onError = { Log.e("log", "error") }
        )
        viewModel.dailyWeather.observe(
            viewLifecycleOwner.lifecycleScope,
            action = { FastAdapterDiffUtil[itemAdapter] = it.map(::DailyItem) },
            onError = { Log.e("log", "error") }
        )
        viewModel.city.observe(
            viewLifecycleOwner.lifecycleScope,
            action = { binding.city.text = it },
            onError = { Log.e("log", "error") }
        )
        viewModel.message.observe(
            viewLifecycleOwner.lifecycleScope,
            action = { Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show() },
            onError = { Log.e("log", "error") }
        )
    }

    private fun getWeatherByLocation() {
        binding.location.setOnClickListener {
            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }
}