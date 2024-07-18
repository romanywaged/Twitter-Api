package com.romanydev.twittertask.usecases.postweet

import com.romanydev.twittertask.data.model.TweetPayload
import com.romanydev.twittertask.data.model.TweetResponse

interface PostTweetUseCase {

    suspend operator fun invoke(postTweetContent:TweetPayload): TweetResponse

}