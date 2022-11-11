package com.ahoy.weathertask.presentation.utils

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.ahoy.weathertask.App
import dagger.android.support.DaggerFragment

abstract class BaseFragment<B : ViewBinding> : DaggerFragment() {
    lateinit var binding: B


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
     //   dialog.init()


    }
    abstract fun getViewBinding(): B

}