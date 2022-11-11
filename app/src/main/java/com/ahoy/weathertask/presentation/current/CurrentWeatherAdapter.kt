package com.ahoy.weathertask.presentation.current

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahoy.weathertask.databinding.WeatherDayItemBinding
import com.ahoy.weathertask.domain.model.response.weatherdatabyday.DayData
import com.ahoy.weathertask.domain.model.response.weatherdatabyday.WeatherDataByDay

class CurrentWeatherAdapter(
    private val days: List<DayData> = listOf(),
) : RecyclerView.Adapter<CurrentWeatherAdapter.ViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentWeatherAdapter.ViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = WeatherDayItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(viewHolder: CurrentWeatherAdapter.ViewHolder, position: Int) {
        viewHolder.bind(days[position])
    }

    override fun getItemCount() = days.size

    inner class ViewHolder(
        private val binding: WeatherDayItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(day: DayData) {
            binding.tvDt.text = day.dt.toString()
            binding.tvTempDay.text = day.temp.day.toString()
            binding.tvTempMax.text = day.temp.max.toString()
            binding.tvTempMin.text = day.temp.min.toString()
        }
    }
}