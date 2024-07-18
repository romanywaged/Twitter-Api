package com.romanydev.twittertask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romanydev.twittertask.data.model.TweetPayload
import com.romanydev.twittertask.usecases.getaccesstoken.GetRequestAccessToken
import com.romanydev.twittertask.usecases.getrequesttoken.GetRequestTokenUseCase
import com.romanydev.twittertask.usecases.postweet.PostTweetUseCase
import com.romanydev.twittertask.utlis.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TwitterViewModel @Inject constructor(
    private val getRequestAccessToken: GetRequestAccessToken,
    private val getRequestTokenUseCase: GetRequestTokenUseCase,
    private val postTweetUseCase: PostTweetUseCase
) : ViewModel() {

    private val _tweetResponse: MutableStateFlow<DataState> = MutableStateFlow(DataState.Empty)
    val tweetResponse: StateFlow<DataState> get() = _tweetResponse

    private val _requestTokenResponse: MutableStateFlow<DataState> = MutableStateFlow(DataState.Empty)
    val requestTokenResponse: StateFlow<DataState> get() = _requestTokenResponse

    private val _accessTokenResponse: MutableStateFlow<DataState> = MutableStateFlow(DataState.Empty)
    val accessTokenResponse: StateFlow<DataState> get() = _accessTokenResponse

    fun getAccessToken() = viewModelScope.launch {
        _accessTokenResponse.value = DataState.Loading
        try {
            val tokenResponse = getRequestAccessToken.invoke()
            _accessTokenResponse.value = DataState.Success(tokenResponse)
        } catch (e: Exception) {
            _accessTokenResponse.value = DataState.Error(e)
        }
    }

    fun getRequestToken() = viewModelScope.launch {
        _requestTokenResponse.value = DataState.Loading
        try {
            val tokenResponse = getRequestTokenUseCase.invoke()
            _requestTokenResponse.value = DataState.Success(tokenResponse)
        } catch (e: Exception) {
            _requestTokenResponse.value = DataState.Error(e)
        }
    }

    fun postTweet(postTweetContent:TweetPayload) = viewModelScope.launch {
        _tweetResponse.value = DataState.Loading
        try {
            val tweetResponse = postTweetUseCase.invoke(postTweetContent)
            _tweetResponse.value = DataState.Success(tweetResponse)
        } catch (e: Exception) {
            _tweetResponse.value = DataState.Error(e)
        }
    }
}