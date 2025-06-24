package com.example.modsen_tasks_aliaksandrstelmakh.ui.state

import com.example.domain.models.CommentDomainModel
import com.example.domain.models.ExceptionsDomainModel

data class CommentState(
    val data: List<CommentDomainModel>? = null,
    val error: ExceptionsDomainModel? = null,
    val isLoading: Boolean = false
)
