package com.ahoy.weathertask.presentation.favouritedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ahoy.weathertask.databinding.FragmentFavouriteDetailsBinding
import com.ahoy.weathertask.presentation.utils.BaseFragment

class FavouriteDetailsFragment : BaseFragment<FragmentFavouriteDetailsBinding>() {

    private val args : FavouriteDetailsFragmentArgs by navArgs()
    override fun getViewBinding() = FragmentFavouriteDetailsBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteDetailsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvCountry.text = args.item.Country
        binding.tvHumidity.text = args.item.Humidity.toString()
        binding.tvName.text = args.item.Name
        binding.tvTempDay.text = args.item.Temp.toString()
    }
}
