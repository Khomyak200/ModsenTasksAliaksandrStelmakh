package com.example.domain.usecases

import com.example.domain.repositories.ILocalRepository

class LoginUseCase(private val loginRepository: ILocalRepository) {
    suspend operator fun invoke(login: String, password: String) = loginRepository.login(login, password)
}