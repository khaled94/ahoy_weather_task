package com.ahoy.weathertask.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ahoy.weathertask.databinding.FragmentSettingsBinding
import com.ahoy.weathertask.presentation.utils.BaseFragment
import javax.inject.Inject

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    override fun getViewBinding() = FragmentSettingsBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        return binding.root
    }
}