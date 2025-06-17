package com.example.modsen_tasks_aliaksandrstelmakh.di.modules

import com.example.data.repositories.LocalRepositoryImpl
import com.example.domain.repositories.ILocalRepository
import com.example.domain.usecases.LoginUseCase
import com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels.Task1ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class MainModule {
    val appModule = module {
        single<ILocalRepository> { LocalRepositoryImpl() }
        single { LoginUseCase(get()) }
        viewModel { Task1ViewModel(get()) }
    }
}