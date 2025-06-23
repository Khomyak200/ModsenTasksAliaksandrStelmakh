package com.example.modsen_tasks_aliaksandrstelmakh.ui.state

import com.example.domain.models.ExceptionsDomainModel
import com.example.domain.models.PostsDomainModel

data class PostState (
    val data: PostsDomainModel? = null,
    val error: ExceptionsDomainModel? = null,
    val isLoading: Boolean = false
)