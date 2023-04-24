package com.example.cities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.cities.databinding.FragmentCityBinding
import com.example.cities.dimodule.CityComponentHolder
import com.example.cities.items.CityItem
import com.example.core.flow.observe
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil

class CityFragment : Fragment() {

    private val viewModel: CityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        CityComponentHolder
            .getComponentImpl()
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupReturnBackButton()
        setupChangeThemeButton()
        setupAddCityButton()
        setupSelectCityFromMapButton()
        setupCitiesList()

        viewModel.loadData()
    }

    private fun setupReturnBackButton() = binding.toolbar.returnBackButton.setOnClickListener {
        viewModel.returnBack()
    }

    private fun setupChangeThemeButton() = binding.toolbar.changeThemeButton.setOnClickListener {
        viewModel.changeTheme()
    }

    private fun setupAddCityButton() = binding.addCityButton.setOnClickListener {
        viewModel.addCity()
    }

    private fun setupSelectCityFromMapButton() = binding.selectCityFromMapButton.setOnClickListener {
        viewModel.selectCityFromMap()
    }

    private fun setupCitiesList() {
        val itemAdapter = ItemAdapter<CityItem>()
        val fastAdapter = FastAdapter.with(itemAdapter)
        binding.citiesList.adapter = fastAdapter
        viewModel.citiesList.observe(
            viewLifecycleOwner.lifecycleScope,
            action = { citiesList ->
                FastAdapterDiffUtil[itemAdapter] =
                    citiesList.map { CityItem(it, viewModel::selectCity) }
            },
            onError = { Log.e("log", "error", it) }
        )
    }
}