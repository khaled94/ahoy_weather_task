package com.ahoy.weathertask.presentation.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahoy.weathertask.databinding.FeatureItemBinding
import com.ahoy.weathertask.presentation.utils.HomeFeature

class HomeAdapter (private val features: List<HomeFeature> = listOf(),
                   private val selectfeature: ((HomeFeature) -> Unit)? = null
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FeatureItemBinding.inflate(layoutInflater)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(viewHolder: HomeAdapter.ViewHolder, position: Int) {
        viewHolder.bind(features[position])
    }

    override fun getItemCount() = features.size

    inner class ViewHolder(
        private val binding: FeatureItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(feature: HomeFeature) {
            binding.tvFeatureName.text = feature.name
            binding.root.setOnClickListener {
                selectfeature?.invoke(feature)
            }
        }
    }
}