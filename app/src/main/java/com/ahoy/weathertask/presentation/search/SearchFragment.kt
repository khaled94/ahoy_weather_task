package com.ahoy.weathertask.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ahoy.weathertask.databinding.FragmentSearchBinding
import com.ahoy.weathertask.domain.model.CityModel
import com.ahoy.weathertask.domain.model.response.weatherdatabycity.WeatherDataByCity
import com.ahoy.weathertask.domain.utils.DataState
import com.ahoy.weathertask.presentation.utils.BaseFragment
import javax.inject.Inject

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var searchViewModel: SearchViewModel
    lateinit var details: WeatherDataByCity

    override fun getViewBinding() = FragmentSearchBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSearchBinding.inflate(layoutInflater)

        searchViewModel =
            ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.svCityName.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.getCurrentWeatherDataByCity(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        searchViewModel.weatherDataByCity.observe(viewLifecycleOwner) { dataState ->
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
                    details = dataState.data
                    binding.tvCountry.text = details.sys.country
                    binding.tvHumidity.text = details.main.humidity.toString()
                    binding.tvTempDay.text = details.main.temp.toString()
                    binding.tvTempMax.text = details.main.temp_max.toString()
                    binding.tvTempMin.text = details.main.temp_min.toString()
                    binding.clCityData.visibility = View.VISIBLE
                }
            }
        }
        binding.btnGo.setOnClickListener {
            //val action = SearchFragmentDirections.actionNavSearchFragmentToFavouriteFragment()
            //findNavController().navigate(action)
            var cityModel = CityModel(
                details.id,
                details.name,
                details.sys.country,
                details.main.humidity,
                details.main.temp
            )
            searchViewModel.AddFavouritetoDB(cityModel)
            findNavController().navigate(SearchFragmentDirections.actionNavSearchFragmentToHomeFragment())
        }
    }

}