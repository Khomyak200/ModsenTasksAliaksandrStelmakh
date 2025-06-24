package com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.mappers.TResult
import com.example.domain.models.PostDomainModel
import com.example.domain.usecases.GetCommentsUseCase
import com.example.domain.usecases.GetDataUseCase
import com.example.modsen_tasks_aliaksandrstelmakh.ui.intent.CommentIntent
import com.example.modsen_tasks_aliaksandrstelmakh.ui.intent.PostIntent
import com.example.modsen_tasks_aliaksandrstelmakh.ui.state.CommentState
import com.example.modsen_tasks_aliaksandrstelmakh.ui.state.PostState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class Task3_2ViewModel (
    private val post: PostDomainModel,
    private val getCommentsUseCase: GetCommentsUseCase
) : ViewModel(){
    private val _state = MutableStateFlow(CommentState())
    val state: StateFlow<CommentState> = _state.asStateFlow()

    fun sendIntent(intent: CommentIntent) {
        when (intent) {
            is CommentIntent.LoadComments -> loadComments()
        }
    }

    init {
        loadComments()
    }

    fun loadComments() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val result = getCommentsUseCase.invoke(post.id)
            when (result) {
                is TResult.Success -> {
                    _state.value = CommentState(data = result.data)
                }
                is TResult.Error -> {
                    _state.value = CommentState(error = result.exception)
                }
            }
            _state.value = _state.value.copy(isLoading = false)
        }
    }
}