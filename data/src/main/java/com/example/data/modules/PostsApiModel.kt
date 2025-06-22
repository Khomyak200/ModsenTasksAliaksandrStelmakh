package com.example.data.modules

import com.example.domain.models.PostDomainModel
import com.example.domain.models.PostsDomainModel
import com.google.gson.annotations.SerializedName

data class PostsApiModel (
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)

fun PostsApiModel.toDomainModel() = PostDomainModel(
    userId = userId,
    id = id,
    title = title,
    body = body
)