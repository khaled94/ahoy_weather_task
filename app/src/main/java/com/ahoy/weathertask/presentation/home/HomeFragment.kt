package com.ahoy.weathertask.presentation.home

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ahoy.weathertask.App
import com.ahoy.weathertask.databinding.FragmentHomeBinding
import com.ahoy.weathertask.presentation.utils.BaseFragment
import com.ahoy.weathertask.presentation.utils.HomeFeature
import com.ahoy.weathertask.presentation.utils.Receiver
import java.util.*
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var homeViewModel: HomeViewModel
    private lateinit var gridLayoutManager: GridLayoutManager
    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        homeViewModel =
            ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvFeatures.layoutManager = gridLayoutManager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFeatures.adapter =
            HomeAdapter(homeViewModel.features) { feature: HomeFeature ->
                selectHomeFeature(feature)
            }
    }

    private fun selectHomeFeature(feature: HomeFeature) {
        when (feature.name) {
            "Current Weather" ->
                findNavController().navigate(HomeFragmentDirections.actionNavHomeFragmentToCurrentweatherfragment())
            "Search" ->
                findNavController().navigate(HomeFragmentDirections.actionNavHomeFragmentToSearchFragment())
            "Favourite" ->
                findNavController().navigate(HomeFragmentDirections.actionNavHomeFragmentToFavouriteFragment())
            "Settings" ->
                findNavController().navigate(HomeFragmentDirections.actionNavHomeFragmentToSettingsFragment())
        }
    }

}