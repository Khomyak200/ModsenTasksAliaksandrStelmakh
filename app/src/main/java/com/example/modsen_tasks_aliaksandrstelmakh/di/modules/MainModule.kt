package com.example.modsen_tasks_aliaksandrstelmakh.di.modules

import com.example.data.repositories.LocalRepositoryImpl
import com.example.data.repositories.RemoteRepositoryImpl
import com.example.domain.models.PostDomainModel
import com.example.domain.repositories.ILocalRepository
import com.example.domain.repositories.IRemoteRepository
import com.example.domain.usecases.GetCommentsUseCase
import com.example.domain.usecases.GetDataUseCase
import com.example.domain.usecases.LoginUseCase
import com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels.Task1ViewModel
import com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels.Task2ViewModel
import com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels.Task3_1ViewModel
import com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels.Task3_2ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class MainModule {
    val appModule = module {
        single<ILocalRepository> { LocalRepositoryImpl() }
        single { LoginUseCase(get()) }
        viewModel { Task1ViewModel(get()) }
        single<IRemoteRepository> { RemoteRepositoryImpl() }
        single { GetDataUseCase(get()) }
        single { GetCommentsUseCase(get()) }
        viewModel { Task2ViewModel(get()) }
        viewModel { Task3_1ViewModel(get()) }
        viewModel { (post: PostDomainModel) ->
            Task3_2ViewModel(post, get())
        }
    }
}