package com.example.modsen_tasks_aliaksandrstelmakh.di.modules

import com.example.modsen_tasks_aliaksandrstelmakh.ui.activities.MainActivity
import com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels.Task1ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class MainModule {
    val appModule = module {
        single { MainActivity() }
        viewModel { Task1ViewModel() }
    }
}