package com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.mappers.TResult
import com.example.domain.usecases.GetDataUseCase
import com.example.modsen_tasks_aliaksandrstelmakh.ui.intent.PostIntent
import com.example.modsen_tasks_aliaksandrstelmakh.ui.state.PostState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class Task2ViewModel(
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
                    val data = result.data
                    val filtered = data.filter {
                        it.title.contains(_state.value.searchQuery, ignoreCase = true) ||
                                it.body.contains(_state.value.searchQuery, ignoreCase = true)
                    }
                    _state.value = PostState(
                        data = data,
                        searchQuery = _state.value.searchQuery,
                        filteredData = filtered
                    )
                }
                is TResult.Error -> {
                    _state.value = PostState(error = result.exception)
                }
            }
            _state.value = _state.value.copy(isLoading = false)
        }
    }

    private fun updateSearchQuery(query: String) {
        val currentData = _state.value.data
        val filtered = currentData?.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.body.contains(query, ignoreCase = true)
        } ?: emptyList()

        _state.value = _state.value.copy(
            searchQuery = query,
            filteredData = filtered
        )
    }
}

