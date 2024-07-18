package com.romanydev.twittertask.usecases.getrequesttoken

import com.romanydev.twittertask.data.model.TokenResponse

interface GetRequestTokenUseCase {

    suspend operator fun invoke(): TokenResponse
}