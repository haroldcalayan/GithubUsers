/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/27/2021
 */

package com.haroldcalayan.githubusers.di.module

import com.haroldcalayan.githubusers.BuildConfig
import com.haroldcalayan.githubusers.data.source.remote.ApiClient
import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(okHttpBuilder: OkHttpClient.Builder) = okHttpBuilder.build()

    @Provides
    @Singleton
    fun provideHttpClientBuilder(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        var okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(httpLoggingInterceptor)

        val dispatcher = Dispatcher()
        dispatcher.maxRequests = MAXIMUM_REQUESTS_CONCURRENTLY

        return okHttpBuilder
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideApiClient(client: OkHttpClient.Builder) = ApiClient(BuildConfig.BASE_APP_URL, client)

    companion object {
        const val MAXIMUM_REQUESTS_CONCURRENTLY = 1
    }
}
