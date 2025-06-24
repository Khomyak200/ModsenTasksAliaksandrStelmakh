package com.example.domain.repositories

import com.example.domain.mappers.TResult
import com.example.domain.models.CommentDomainModel
import com.example.domain.models.ExceptionsDomainModel
import com.example.domain.models.PostDomainModel

interface IRemoteRepository {
    suspend fun getData(): TResult<List<PostDomainModel>, ExceptionsDomainModel>
    suspend fun getComments(postId:Int): TResult<List<CommentDomainModel>, ExceptionsDomainModel>
}