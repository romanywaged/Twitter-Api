package com.romanydev.twittertask.data.repositories

import com.romanydev.twittertask.data.model.TokenResponse
import com.romanydev.twittertask.data.model.TweetPayload
import com.romanydev.twittertask.data.model.TweetResponse
import com.romanydev.twittertask.data.remote.TwitterService
import javax.inject.Inject

class TwitterRepository @Inject constructor(
    private val twitterService: TwitterService
) {

    suspend fun getRequestToken(): TokenResponse = twitterService.getRequestToken()
//        val response =
//        if (response.isSuccessful) {
//            return response.body()!!
//        } else {
//            throw Exception("Failed to get request token")
//        }
//    }

    suspend fun getAccessToken(): TokenResponse = twitterService.getAccessToken()
//        val response =
//        if (response.isSuccessful) {
//            return response.body()!!
//        } else {
//            throw Exception("Failed to get access token")
//        }
//    }

    suspend fun postTweet( payload: TweetPayload): TweetResponse = twitterService.postTweet( payload)
//        val payload = TweetPayload(text)
//        val response = twitterService.postTweet(payload)
//        if (response.isSuccessful) {
//            return response.body()!!
//        } else {
//            throw Exception("Failed to post tweet")
//        }
//    }
}
