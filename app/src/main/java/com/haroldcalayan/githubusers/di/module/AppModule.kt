/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/27/2021
 */

package com.haroldcalayan.githubusers.di.module

import android.content.Context
import androidx.room.Room
import com.haroldcalayan.githubusers.GithubUsersApp
import com.haroldcalayan.githubusers.data.source.local.GithubUsersDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: GithubUsersApp, private val databaseName: String) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): GithubUsersDatabase {
        return Room.databaseBuilder(context, GithubUsersDatabase::class.java, databaseName).build()
    }
}