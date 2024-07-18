package com.romanydev.twittertask.utlis

import com.romanydev.twittertask.data.model.TokenResponse
import com.romanydev.twittertask.data.model.TweetResponse

sealed class DataState {

    object Loading : DataState()

    object Empty : DataState()

    class Success<T>(val data: T) : DataState()

    data class Error(val exception: Exception) : DataState()

}