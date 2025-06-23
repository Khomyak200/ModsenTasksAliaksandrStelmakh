package com.example.domain.models

data class PostDomainModel(
    val userId: Int,
    val id:Int,
    val title: String,
    val body: String
)
data class PostsDomainModel(
    val posts: List<PostDomainModel>
)
