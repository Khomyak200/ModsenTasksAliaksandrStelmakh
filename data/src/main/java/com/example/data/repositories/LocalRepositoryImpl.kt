package com.example.data.repositories

import com.example.data.modules.LoginDbModel
import com.example.domain.models.UserDomainModel
import com.example.domain.repositories.ILocalRepository
import kotlinx.coroutines.delay

class LocalRepositoryImpl:ILocalRepository {

    private val validCredentials = listOf(
        LoginDbModel("user1", "pass1"),
        LoginDbModel("admin", "1234"),
        LoginDbModel("user2", "pass2")
    )

    override suspend fun login(login: String, password: String): Result<UserDomainModel> {
        delay(5000)

        val isValid = validCredentials.any { it.login == login && it.password == password }

        return if (isValid) {
            Result.success(UserDomainModel(login, password))
        } else {
            Result.failure(Exception("Неверные логин или пароль"))
        }
    }
}