/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/27/2021
 */

package com.haroldcalayan.githubusers.di.module

import com.haroldcalayan.githubusers.data.repository.UserRepository
import com.haroldcalayan.githubusers.data.repository.UserRepositoryImpl
import com.haroldcalayan.githubusers.data.source.local.GithubUsersDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(appDatabase: GithubUsersDatabase) : UserRepository =
        UserRepositoryImpl(appDatabase)
}