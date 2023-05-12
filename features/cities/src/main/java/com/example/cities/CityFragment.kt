package com.example.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.cities.dimodule.CityComponentHolder
import com.example.core.flow.observe

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
        return ComposeView(context =requireContext()).apply { 
            setContent { 
                CitiesScreen(viewModel = viewModel)
            }
        }
    }
}