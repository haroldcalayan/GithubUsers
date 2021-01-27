/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/27/2021
 */

package com.haroldcalayan.githubusers.data.source.remote

import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory
import com.github.leonardoxh.livedatacalladapter.LiveDataResponseBodyConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient(
    private val baseURL: String,
    private val okHttpBuilder: OkHttpClient.Builder
) {

    private lateinit var apiService: ApiService

    init {
        createClient(baseURL)
    }

    private fun createClient(baseUrl: String) {
        val retrofit = Retrofit.Builder()
            .client(okHttpBuilder.build())
            .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
            .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getService(): ApiService? {
        return apiService
    }
}
