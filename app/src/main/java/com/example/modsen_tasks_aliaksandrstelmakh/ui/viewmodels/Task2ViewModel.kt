package com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.mappers.TResult
import com.example.modsen_tasks_aliaksandrstelmakh.R
import com.example.domain.models.ExceptionsDomainModel
import com.example.data.repositories.RemoteRepositoryImpl
import com.example.domain.usecases.GetDataUseCase
import com.example.modsen_tasks_aliaksandrstelmakh.ui.intent.PostIntent
import com.example.modsen_tasks_aliaksandrstelmakh.ui.state.PostState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class Task2ViewModel(
    private val getDataUseCase: GetDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PostState())
    val state: StateFlow<PostState> = _state.asStateFlow()

    fun sendIntent(intent: PostIntent) {
        when (intent) {
            is PostIntent.LoadPosts -> loadPosts()
        }
    }

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val result = getDataUseCase.invoke()
            when (result) {
                is TResult.Success -> {
                    _state.value = PostState(data = result.data)
                }
                is TResult.Error -> {
                    _state.value = PostState(error = result.exception)
                }
            }
            _state.value = _state.value.copy(isLoading = false)
        }
    }

}


fun ExceptionsDomainModel.parseToString() = when (this) {
    is ExceptionsDomainModel.NoInternet -> R.string.app_name
    is ExceptionsDomainModel.Other -> R.string.app_name
    is ExceptionsDomainModel.NoAuth -> R.string.app_name
}