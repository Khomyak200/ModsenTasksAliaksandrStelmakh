package com.example.domain.usecases

import com.example.domain.repositories.ILocalRepository
import com.example.domain.repositories.IRemoteRepository

class GetDataUseCase(
    private val remoteRepository: IRemoteRepository
) {
    suspend operator fun invoke() = remoteRepository.getData()
}