package com.amir.calculatorapp.di

import com.amir.calculatorapp.base.BaseViewModel
import com.amir.calculatorapp.ui.activity.MainViewModel
import com.amir.calculatorapp.ui.fragments.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { BaseViewModel() }
    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}