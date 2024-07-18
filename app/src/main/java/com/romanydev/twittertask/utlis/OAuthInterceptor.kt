package com.romanydev.twittertask.utlis

import okhttp3.Interceptor
import okhttp3.Response

class OAuthInterceptor(
    private val consumerKey: String,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Build the OAuth headers

        val oauthHeader = "OAuth " +
                "oauth_consumer_key=\"$consumerKey\", " +
                "oauth_signature_method=\"HMAC-SHA1\""

        val headers = originalRequest.headers.newBuilder()
            .add("Authorization", oauthHeader)
            .build()

        // Build the new request with the OAuth headers
        val newRequest = originalRequest.newBuilder()
            .headers(headers)
            .build()

        return chain.proceed(newRequest)
    }
}
