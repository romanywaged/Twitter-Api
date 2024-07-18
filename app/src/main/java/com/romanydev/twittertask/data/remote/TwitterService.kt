package com.romanydev.twittertask.data.remote

import com.romanydev.twittertask.data.model.TokenResponse
import com.romanydev.twittertask.data.model.TweetPayload
import com.romanydev.twittertask.data.model.TweetResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface TwitterService {

    @POST("oauth/request_token")
    suspend fun getRequestToken(@Header("H")
        @Query("oauth_callback") authCallback:String = "oob",
                                @Query("x_auth_access_type") authAccess:String = "write"): TokenResponse

    @POST("oauth/access_token")
    suspend fun getAccessToken(): TokenResponse

    @POST("2/tweets")
    suspend fun postTweet(
        @Body payload: TweetPayload
    ): TweetResponse
}