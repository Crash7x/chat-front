package com.example.weather.adapter.dailyweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.weather.R
import com.example.weather.adapter.hoursweather.HoursItem
import com.example.weather.databinding.DailyItemBinding
import com.example.weather.usecases.common.DailyWeather
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import java.text.SimpleDateFormat
import java.util.*

class DailyItem(private val item: DailyWeather) :
    AbstractBindingItem<DailyItemBinding>() {

    override var identifier: Long
        get() = item.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.daily_item

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): DailyItemBinding {
        return DailyItemBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: DailyItemBinding, payloads: List<Any>) {
        val context = binding.root.context
        val sdf = SimpleDateFormat("dd MMMM, E", Locale("ru"))
        binding.date.text = sdf.format(item.date)
        binding.minTemp.text = context.getString(R.string.temp, item.minTemp)
        binding.maxTemp.text = context.getString(R.string.temp, item.maxTemp)
        binding.image.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                item.icon.image
            )
        )

        val hoursAdapter = ItemAdapter<HoursItem>()
        val fastAdapter = FastAdapter.with(hoursAdapter)
        FastAdapterDiffUtil[hoursAdapter] = item.hoursList.map(::HoursItem)
        binding.recyclerHours.adapter = fastAdapter
    }

}
