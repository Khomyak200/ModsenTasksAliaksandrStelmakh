package com.example.data.repositories

import com.example.data.api.RetrofitAPI
import com.example.data.modules.PostsApiModel
import com.example.data.modules.toDomainModel
import com.example.domain.mappers.TResult
import com.example.domain.models.ExceptionsDomainModel
import com.example.domain.models.PostsDomainModel
import com.example.domain.models.toExceptionsDomainModel
import com.example.domain.repositories.IRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteRepositoryImpl: IRemoteRepository {

     override suspend fun getData(): TResult<PostsDomainModel, ExceptionsDomainModel> = withContext(Dispatchers.IO) {
        runCatching {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService: RetrofitAPI = retrofit.create(RetrofitAPI::class.java)

            val postsApiList = apiService.getData()
            val domainPosts = postsApiList.map { it.toDomainModel() }
            val postsDomainModel = PostsDomainModel(posts = domainPosts)

            TResult.Success<PostsDomainModel, ExceptionsDomainModel>(data = postsDomainModel)
        }.getOrElse {
            TResult.Error(it.toExceptionsDomainModel())
        }
    }

}