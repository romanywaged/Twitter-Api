package com.romanydev.twittertask.di

import com.romanydev.twittertask.data.repositories.TwitterRepository
import com.romanydev.twittertask.usecases.getaccesstoken.GetRequestAccessToken
import com.romanydev.twittertask.usecases.getaccesstoken.GetRequestAccessTokenImpl
import com.romanydev.twittertask.usecases.getrequesttoken.GetRequestTokenImpl
import com.romanydev.twittertask.usecases.getrequesttoken.GetRequestTokenUseCase
import com.romanydev.twittertask.usecases.postweet.PostTweetImpl
import com.romanydev.twittertask.usecases.postweet.PostTweetUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
    @Singleton
    @Provides
    fun provideRequestAccessToken(twitterRepository: TwitterRepository): GetRequestAccessToken =
        GetRequestAccessTokenImpl(twitterRepository)

    @Singleton
    @Provides
    fun provideRequestToken(twitterRepository: TwitterRepository): GetRequestTokenUseCase =
        GetRequestTokenImpl(twitterRepository)

    @Singleton
    @Provides
    fun providePostTweet(twitterRepository: TwitterRepository): PostTweetUseCase =
        PostTweetImpl(twitterRepository)
}