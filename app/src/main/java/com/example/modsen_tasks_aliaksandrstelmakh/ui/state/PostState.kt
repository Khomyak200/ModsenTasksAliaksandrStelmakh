package com.example.modsen_tasks_aliaksandrstelmakh.ui.state

import com.example.domain.models.ExceptionsDomainModel
import com.example.domain.models.PostDomainModel

data class PostState (
    val data: List<PostDomainModel>? = null,
    val error: ExceptionsDomainModel? = null,
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val filteredData: List<PostDomainModel> = emptyList()
)