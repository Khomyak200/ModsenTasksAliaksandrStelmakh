package com.example.modsen_tasks_aliaksandrstelmakh.ui.intent

sealed class LoginIntent {
    data class UpdateLogin(val login: String) : LoginIntent()
    data class UpdatePassword(val password: String) : LoginIntent()
    object Submit : LoginIntent()
}