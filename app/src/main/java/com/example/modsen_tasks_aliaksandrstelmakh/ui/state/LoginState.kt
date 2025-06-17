package com.example.modsen_tasks_aliaksandrstelmakh.ui.state

data class LoginState(
    val login: String = "",
    val password: String = "",
    val isButtonEnabled: Boolean = false
)