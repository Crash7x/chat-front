package com.example.weather.adapter.hoursweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.weather.R
import com.example.weather.databinding.HoursItemBinding
import com.example.weather.usecases.common.TimeWeather
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import java.text.SimpleDateFormat
import java.util.*

class HoursItem(private val item: TimeWeather) :
    AbstractBindingItem<HoursItemBinding>() {

    override var identifier: Long
        get() = item.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.hours_item

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?) =
        HoursItemBinding.inflate(inflater, parent, false)

    override fun bindView(binding: HoursItemBinding, payloads: List<Any>) {
        val context = binding.root.context
        val sdf = SimpleDateFormat("HH:mm", Locale("ru"))
        binding.hoursTime.text = sdf.format(item.timeHours)
        binding.hoursTemp.text = context.getString(R.string.temp, item.tempHours)
        binding.hoursImage.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                item.iconHours.image
            )
        )
    }

}