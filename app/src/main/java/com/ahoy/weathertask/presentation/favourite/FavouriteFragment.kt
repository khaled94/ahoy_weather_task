package com.ahoy.weathertask.presentation.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahoy.weathertask.databinding.FragmentFavouritesBinding
import com.ahoy.weathertask.domain.model.CityModel
import com.ahoy.weathertask.domain.utils.DataState
import com.ahoy.weathertask.presentation.home.HomeAdapter
import com.ahoy.weathertask.presentation.home.HomeFragmentDirections
import com.ahoy.weathertask.presentation.search.SearchFragment
import com.ahoy.weathertask.presentation.utils.BaseFragment
import com.ahoy.weathertask.presentation.utils.HomeFeature
import javax.inject.Inject

class FavouriteFragment : BaseFragment<FragmentFavouritesBinding>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var favouriteViewModel: FavouriteViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun getViewBinding() = FragmentFavouritesBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(layoutInflater)
        favouriteViewModel =
            ViewModelProvider(this, viewModelFactory)[FavouriteViewModel::class.java]
       linearLayoutManager = LinearLayoutManager(requireContext())
       binding.rvCities.layoutManager = linearLayoutManager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favouriteViewModel.getFavourites()

        favouriteViewModel.favourites.observe(viewLifecycleOwner) { favourites ->
            if( favourites.isEmpty())
                Toast.makeText(requireContext(), "No names to show!", Toast.LENGTH_SHORT).show()
            else{
                binding.rvCities.adapter =
                    FavouriteAdapter(favourites) { city: CityModel ->
                        selectCity(city)
                    }
            }

        }

        }

    private fun selectCity(city: CityModel) {
        val action = FavouriteFragmentDirections.actionNavFavouriteFragmentToFavouriteDetailsFragment(city)
        findNavController().navigate(action)
    }
}