package com.example.data.repositories

import android.util.Log
import com.example.data.api.RetrofitAPI
import com.example.data.api.RetrofitClient
import com.example.data.modules.CommentApiModel
import com.example.data.modules.PostsApiModel
import com.example.data.modules.toDomainModel
import com.example.domain.mappers.TResult
import com.example.domain.models.CommentDomainModel
import com.example.domain.models.ExceptionsDomainModel
import com.example.domain.models.PostDomainModel
import com.example.domain.repositories.IRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.net.ConnectException
import java.net.UnknownHostException

class RemoteRepositoryImpl: IRemoteRepository {

    interface RetrofitAPI {
        @GET("posts")
        suspend fun getData(): List<PostsApiModel>

        @GET("comments")
        suspend fun getComments(@Query("postId") postId: Int): List<CommentApiModel>
    }

    class RetrofitClient {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService: RetrofitAPI = retrofit.create(RetrofitAPI::class.java)
    }

     override suspend fun getData(): TResult<List<PostDomainModel>, ExceptionsDomainModel> = withContext(Dispatchers.IO) {
        runCatching {
            delay(3000)
            val apiService = RetrofitClient().apiService

            val postsApiList = apiService.getData()
            val domainPosts = postsApiList.map { it.toDomainModel() }

            TResult.Success<List<PostDomainModel>, ExceptionsDomainModel>(data = domainPosts)
        }.getOrElse {
            TResult.Error(it.toExceptionsDomainModel())
        }
    }
    override suspend fun getComments(postId:Int): TResult<List<CommentDomainModel>, ExceptionsDomainModel> = withContext(Dispatchers.IO) {
        runCatching {
            val apiService = RetrofitClient().apiService

            val commentsApiList = apiService.getComments(postId)
            val domainComments = commentsApiList.map { it.toDomainModel() }

            TResult.Success<List<CommentDomainModel>, ExceptionsDomainModel>(data = domainComments)
        }.getOrElse {
            TResult.Error(it.toExceptionsDomainModel())
        }
    }

}

fun Throwable.toExceptionsDomainModel(): ExceptionsDomainModel {
    Log.e("!!", this.stackTraceToString())
    return when (this) {

        is UnknownHostException, is ConnectException -> ExceptionsDomainModel.NoInternet(this)
        is retrofit2.HttpException -> {
            when (this.code()) {
                401 -> ExceptionsDomainModel.NoAuth(this)
                else -> ExceptionsDomainModel.Other(this)
            }
        }

        is ExceptionsDomainModel -> this
        else -> ExceptionsDomainModel.Other(this)
    }
}

