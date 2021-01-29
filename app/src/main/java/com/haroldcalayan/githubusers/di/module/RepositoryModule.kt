/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/27/2021
 */

package com.haroldcalayan.githubusers.di.module

import com.haroldcalayan.githubusers.data.repository.note.NoteRepository
import com.haroldcalayan.githubusers.data.repository.note.NoteRepositoryImpl
import com.haroldcalayan.githubusers.data.repository.user.UserRepository
import com.haroldcalayan.githubusers.data.repository.user.UserRepositoryImpl
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

    @Provides
    @Singleton
    fun provideNoteRepository(appDatabase: GithubUsersDatabase) : NoteRepository =
        NoteRepositoryImpl(appDatabase)
}