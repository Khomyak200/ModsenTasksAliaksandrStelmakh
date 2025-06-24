package com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repositories.LocalRepositoryImpl
import com.example.domain.usecases.LoginUseCase
import com.example.modsen_tasks_aliaksandrstelmakh.ui.SingleFlowEvent
import com.example.modsen_tasks_aliaksandrstelmakh.ui.intent.LoginIntent
import com.example.modsen_tasks_aliaksandrstelmakh.ui.state.LoginState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

class Task1ViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _event = SingleFlowEvent<LoginEvent>(viewModelScope)
    val event = _event.flow

    init {
        updateButtonState()
    }

    fun processIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.UpdateLogin -> {
                _state.value = _state.value.copy(login = intent.login)
                updateButtonState()
            }
            is LoginIntent.UpdatePassword -> {
                _state.value = _state.value.copy(password = intent.password)
                updateButtonState()
            }
            is LoginIntent.Submit -> {
                login(_state.value.login, _state.value.password)
            }
        }
    }

    fun login(login: String, password: String) {
        viewModelScope.launch {
            val result = loginUseCase.invoke(login, password)
            if (result.isSuccess) {
                _event.emit(LoginEvent.NavigateToSuccessScreen)
            } else {
                _event.emit(LoginEvent.ShowError(result.exceptionOrNull()?.message ?: "Ошибка"))
            }
        }
    }

    private fun updateButtonState() {
        _state.value = _state.value.copy(isButtonEnabled = isButtonEnabled())
    }

    private fun isButtonEnabled(): Boolean {
        return _state.value.login.isNotBlank() && _state.value.password.isNotBlank()
    }

}

sealed interface LoginEvent {
    data class ShowError(val error: String) : LoginEvent
    data object NavigateToSuccessScreen : LoginEvent
}

