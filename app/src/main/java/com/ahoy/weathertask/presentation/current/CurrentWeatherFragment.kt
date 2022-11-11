package com.ahoy.weathertask.presentation.current

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahoy.weathertask.databinding.FragmentCurrentWeatherBinding
import com.ahoy.weathertask.domain.utils.DataState
import com.ahoy.weathertask.presentation.home.HomeAdapter
import com.ahoy.weathertask.presentation.utils.BaseFragment
import javax.inject.Inject

class CurrentWeatherFragment : BaseFragment<FragmentCurrentWeatherBinding>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var currentWeatherViewModel: CurrentWeatherViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var myLocation: Location
    var latitude: Double = 0.0
    var longitude: Double = 0.0

    override fun getViewBinding() = FragmentCurrentWeatherBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentWeatherBinding.inflate(layoutInflater)

        currentWeatherViewModel =
            ViewModelProvider(this, viewModelFactory)[CurrentWeatherViewModel::class.java]

        if (isLocationPermissionGranted()) {
            getLocation()
        } else
            isLocationPermissionGranted()

        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.rvDaily.layoutManager = linearLayoutManager

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentWeatherViewModel.weatherData.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Empty -> {
                    Toast.makeText(requireContext(), "No names to show!", Toast.LENGTH_SHORT).show()

                }
                is DataState.Error -> {
                    Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT)
                        .show()
                }
                is DataState.Loading -> {
                }
                is DataState.Success -> {
                    val details = dataState.data
                    binding.tvHumidity.text = details.current.humidity.toString()
                    binding.tvPressure.text = details.current.pressure.toString()
                    binding.tvTemp.text = details.current.temp.toString()
                    binding.tvWind.text = details.current.wind_speed.toString()
                    longitude = details.lon
                    latitude = details.lat
                }
            }
        }

        binding.btnGo.setOnClickListener {
            currentWeatherViewModel.getWeatherDataByDay(
                latitude,
                longitude,
                binding.etDays.text.toString().toInt()
            )
        }

        currentWeatherViewModel.weatherDataByDay.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Empty -> {
                    Toast.makeText(requireContext(), "No names to show!", Toast.LENGTH_SHORT).show()

                }
                is DataState.Error -> {
                    Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT)
                        .show()
                }
                is DataState.Loading -> {
                }
                is DataState.Success -> {
                    val details = dataState.data
                    binding.rvDaily.adapter =
                        CurrentWeatherAdapter(details.list)

                }
            }
        }
    }

    private fun isLocationPermissionGranted(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                0
            )
            false
        } else {
            true
        }
    }

    private fun getLocation() {
        var currentLocation: Location? = null
        var locationByGps: Location? = null
        var locationByNetwork: Location? = null
        val locationManager: LocationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        val gpsLocationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                locationByGps = location
                currentLocation = location
                onLocationReceived(location)

            }

            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        val networkLocationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                locationByNetwork = location
                currentLocation = location
                onLocationReceived(location)
            }

            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        if (hasGps) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                0F,
                gpsLocationListener
            )
        }
        if (hasNetwork) {
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                5000,
                0F,
                networkLocationListener
            )
        }

        val lastKnownLocationByGps =
            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        lastKnownLocationByGps?.let {
            locationByGps = lastKnownLocationByGps
        }
        val lastKnownLocationByNetwork =
            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        lastKnownLocationByNetwork?.let {
            locationByNetwork = lastKnownLocationByNetwork
        }
        if (locationByGps != null && locationByNetwork != null) {
            currentLocation = if (locationByGps!!.accuracy > locationByNetwork!!.accuracy) {
                locationByGps

                // use latitude and longitude as per your need
            } else {
                locationByNetwork
                // use latitude and longitude as per your need
            }
        }
    }

    fun onLocationReceived(location: Location) {
        myLocation = location
        currentWeatherViewModel.getCurrentWeatherData(myLocation.latitude, myLocation.longitude)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        currentWeatherViewModel.weatherData.removeObservers(viewLifecycleOwner)

    }

}