package com.romanydev.twittertask.usecases.getaccesstoken

import com.romanydev.twittertask.data.model.TokenResponse

interface GetRequestAccessToken {

    suspend operator fun invoke(): TokenResponse
}