package com.example.data.api

import com.example.data.modules.PostsApiModel
import retrofit2.http.GET

interface RetrofitAPI {
    @GET("posts")
    suspend fun getData(): List<PostsApiModel>
}
