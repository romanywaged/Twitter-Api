package com.romanydev.twittertask.di

import com.romanydev.twittertask.BuildConfig
import com.romanydev.twittertask.data.remote.TwitterService
import com.romanydev.twittertask.utlis.CONSUMER_KEY
import com.romanydev.twittertask.utlis.CONSUMER_SECRET
import com.romanydev.twittertask.utlis.OAuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOAuthInterceptor(): OAuthInterceptor {
        // Replace these with your actual keys
        val consumerKey = CONSUMER_KEY
        val consumerSecret = CONSUMER_SECRET
        return OAuthInterceptor(consumerKey)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(oAuthInterceptor: OAuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(oAuthInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
    }

    @Provides
    @Singleton
    fun provideTwitterService(retrofit: Retrofit): TwitterService {
        return retrofit.create(TwitterService::class.java)
    }
}
