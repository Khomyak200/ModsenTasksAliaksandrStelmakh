package com.example.domain.repositories

import com.example.domain.mappers.TResult
import com.example.domain.models.ExceptionsDomainModel
import com.example.domain.models.PostsDomainModel

interface IRemoteRepository {
    suspend fun getData(): TResult<PostsDomainModel, ExceptionsDomainModel>
}