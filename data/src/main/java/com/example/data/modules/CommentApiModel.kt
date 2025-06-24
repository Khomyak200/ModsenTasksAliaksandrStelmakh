package com.example.data.modules

import com.example.domain.models.CommentDomainModel
import com.google.gson.annotations.SerializedName

data class CommentApiModel(
    @SerializedName("postId") val postId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("body") val body: String
)

fun CommentApiModel.toDomainModel() = CommentDomainModel(
    postId = postId,
    id = id,
    name = name,
    email = email,
    body = body
)
