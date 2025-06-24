package com.example.domain.usecases

import com.example.domain.repositories.IRemoteRepository

class GetCommentsUseCase(private val remoteRepository: IRemoteRepository) {
    suspend operator fun invoke(postId:Int) = remoteRepository.getComments(postId)
}