package com.example.modsen_tasks_aliaksandrstelmakh.ui.intent

sealed class PostIntent {
    object LoadPosts : PostIntent()
    data class UpdateSearchQuery(val query: String) : PostIntent()
}