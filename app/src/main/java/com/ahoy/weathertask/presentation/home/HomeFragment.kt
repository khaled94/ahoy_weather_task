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

    private fun createAlarm() {
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 6)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        val alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val receiverIntent = Intent(requireContext(), Receiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            0,
            receiverIntent,
            0
        )
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "Weather_App"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel =
                NotificationChannel("Weather_App_CHANNEL", name, importance)
            val notificationManager =
                activity!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}