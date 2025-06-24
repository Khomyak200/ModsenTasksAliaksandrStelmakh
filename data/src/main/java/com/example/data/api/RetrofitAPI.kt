package com.example.data.api

import com.example.data.modules.CommentApiModel
import com.example.data.modules.PostsApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {
    @GET("posts")
    suspend fun getData(): List<PostsApiModel>

    @GET("comments")
    suspend fun getComments(@Query("postId") postId: Int): List<CommentApiModel>
}
