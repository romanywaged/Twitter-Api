package com.romanydev.twittertask.usecases.getrequesttoken

import com.romanydev.twittertask.data.model.TokenResponse
import com.romanydev.twittertask.data.repositories.TwitterRepository
import javax.inject.Inject

class GetRequestTokenImpl @Inject constructor(private val twitterRepository: TwitterRepository) :GetRequestTokenUseCase{
    override suspend fun invoke(): TokenResponse {
        return twitterRepository.getAccessToken()
    }
}