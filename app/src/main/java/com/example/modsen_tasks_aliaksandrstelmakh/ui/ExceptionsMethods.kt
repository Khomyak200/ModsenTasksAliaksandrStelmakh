package com.example.modsen_tasks_aliaksandrstelmakh.ui

import com.example.domain.models.ExceptionsDomainModel
import com.example.modsen_tasks_aliaksandrstelmakh.R

class ExceptionsMethods {
    fun ExceptionsDomainModel.parseToString() = when (this) {
        is ExceptionsDomainModel.NoInternet -> R.string.exception_nointernet
        is ExceptionsDomainModel.Other -> R.string.exception_others
        is ExceptionsDomainModel.NoAuth -> R.string.exception_noauth
    }
}