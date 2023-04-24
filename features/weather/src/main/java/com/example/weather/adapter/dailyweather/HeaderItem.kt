package com.example.weather.adapter.dailyweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.weather.R
import com.example.weather.databinding.HeaderItemBinding
import com.example.weather.usecases.common.TodayWeather
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import java.text.SimpleDateFormat
import java.util.*

class HeaderItem(private val item: TodayWeather) :
    AbstractBindingItem<HeaderItemBinding>() {

    override var identifier: Long
        get() = item.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.header_item

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?) =
        HeaderItemBinding.inflate(inflater, parent, false)

    override fun bindView(binding: HeaderItemBinding, payloads: List<Any>) {
        val context = binding.root.context
        val sdf = SimpleDateFormat("dd MMMM, E", Locale("ru"))
        binding.todayDate.text = context.getString(
            R.string.today, sdf.format(item.date).toString()
        )
        binding.todayTemp.text = context.getString(R.string.temp, item.temp)
        binding.todayDescription.text = item.description
        binding.todayIcon.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                item.icon.image
            )
        )
    }

}
