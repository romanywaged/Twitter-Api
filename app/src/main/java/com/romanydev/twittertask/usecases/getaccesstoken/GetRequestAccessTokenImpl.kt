package com.romanydev.twittertask.usecases.getaccesstoken

import com.romanydev.twittertask.data.model.TokenResponse
import com.romanydev.twittertask.data.repositories.TwitterRepository
import javax.inject.Inject

class GetRequestAccessTokenImpl @Inject constructor(private val twitterRepository: TwitterRepository):GetRequestAccessToken {
    override suspend fun invoke(): TokenResponse = twitterRepository.getRequestToken()
}