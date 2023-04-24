package com.example.cities.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.cities.CityElement
import com.example.cities.R
import com.example.cities.databinding.ItemCityBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class CityItem(
    private val item: CityElement,
    private val callback: (CityElement) -> Unit
) : AbstractBindingItem<ItemCityBinding>() {

    override val type: Int
        get() = R.id.city_item

    override var identifier: Long
        get() = item.hashCode().toLong()
        set(value) {}

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?) =
        ItemCityBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemCityBinding, payloads: List<Any>) {
        val context = binding.root.context
        binding.cityName.text = item.cityName
        binding.minimumTemperature.text = context.getString(R.string.temp, item.minimumTemperature)
        binding.maximumTemperature.text = context.getString(R.string.temp, item.maximumTemperature)
        binding.weatherIcon.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                item.weatherIcon.image
            )
        )
        binding.root.setOnClickListener {
            callback(item)
        }
    }

}