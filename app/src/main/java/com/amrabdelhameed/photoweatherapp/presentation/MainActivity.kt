package com.amrabdelhameed.photoweatherapp.presentation

import com.amrabdelhameed.photoweatherapp.BR
import com.amrabdelhameed.photoweatherapp.R
import com.amrabdelhameed.photoweatherapp.databinding.ActivityMainBinding
import com.amrabdelhameed.photoweatherapp.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()
}