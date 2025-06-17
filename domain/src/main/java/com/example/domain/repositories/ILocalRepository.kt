package com.example.domain.repositories

import com.example.domain.models.UserDomainModel

interface ILocalRepository {
    suspend fun login(login: String, password: String): Result<UserDomainModel>
}