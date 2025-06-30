package com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels

import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.mappers.TResult
import com.example.domain.usecases.GetDataUseCase
import com.example.modsen_tasks_aliaksandrstelmakh.ui.intent.PostIntent
import com.example.modsen_tasks_aliaksandrstelmakh.ui.state.PostState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class Task3_1ViewModel(
    private val getDataUseCase: GetDataUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(PostState())
    val state: StateFlow<PostState> = _state.asStateFlow()

    fun sendIntent(intent: PostIntent) {
        when (intent) {
            is PostIntent.LoadPosts -> loadPosts()
            is PostIntent.UpdateSearchQuery -> updateSearchQuery(intent.query)
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
                    _state.value = PostState(data = result.data, searchQuery = _state.value.searchQuery )
                }
                is TResult.Error -> {
                    _state.value = PostState(error = result.exception)
                }
            }
            _state.value = _state.value.copy(isLoading = false)
        }
    }

    private fun updateSearchQuery(query: String) {
        _state.value = _state.value.copy(searchQuery = query)
    }
}