package com.ahoy.weathertask.presentation.settings

import android.os.Bundle
import android.widget.RadioButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahoy.weathertask.data.Constants
import com.ahoy.weathertask.databinding.FragmentSettingsBinding
import com.ahoy.weathertask.presentation.utils.BaseFragment

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    override fun getViewBinding() = FragmentSettingsBinding.inflate(layoutInflater)
    lateinit var radioButton: RadioButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        binding.submitButton.setOnClickListener {
            val selectedOption: Int = binding.radioGroup1.checkedRadioButtonId
            radioButton =  binding.root.findViewById(selectedOption)

        }
        return binding.root
    }

}