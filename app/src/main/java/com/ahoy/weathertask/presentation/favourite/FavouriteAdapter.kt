package com.ahoy.weathertask.presentation.favourite

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahoy.weathertask.databinding.FavouriteItemBinding
import com.ahoy.weathertask.domain.model.CityModel


class FavouriteAdapter (private val cities: List<CityModel> = listOf(),
                        private val selectCity: ((CityModel) -> Unit)? = null
) : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteAdapter.ViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FavouriteItemBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(viewHolder: FavouriteAdapter.ViewHolder, position: Int) {
        viewHolder.bind(cities[position])
    }

    override fun getItemCount() = cities.size

    inner class ViewHolder(
        private val binding: FavouriteItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(city : CityModel) {
            binding.tvFavoriteCityName.text = city.Name
            binding.root.setOnClickListener {
                selectCity?.invoke(city)
            }
        }
    }
}