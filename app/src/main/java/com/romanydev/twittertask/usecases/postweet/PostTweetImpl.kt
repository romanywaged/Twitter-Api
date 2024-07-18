package com.romanydev.twittertask.usecases.postweet

import com.romanydev.twittertask.data.model.TweetPayload
import com.romanydev.twittertask.data.model.TweetResponse
import com.romanydev.twittertask.data.repositories.TwitterRepository
import javax.inject.Inject

class PostTweetImpl @Inject constructor(private val twitterRepository: TwitterRepository) :PostTweetUseCase {
    override suspend fun invoke(

        postTweetContent: TweetPayload
    ): TweetResponse {
        return twitterRepository.postTweet(postTweetContent)
    }
}