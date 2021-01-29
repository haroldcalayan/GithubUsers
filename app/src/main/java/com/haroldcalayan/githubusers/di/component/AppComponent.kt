/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/27/2021
 */

package com.haroldcalayan.githubusers.di.component

import com.haroldcalayan.githubusers.data.repository.user.UserRepositoryImpl
import com.haroldcalayan.githubusers.di.module.AppModule
import com.haroldcalayan.githubusers.di.module.MediaModule
import com.haroldcalayan.githubusers.di.module.NetworkModule
import com.haroldcalayan.githubusers.di.module.RepositoryModule
import com.haroldcalayan.githubusers.ui.profile.ProfileViewModel
import com.haroldcalayan.githubusers.ui.user.UserViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    MediaModule::class
])
interface AppComponent {

    // Classes that can be injected by this Component
    fun inject(viewModel: UserViewModel)
    fun inject(viewModel: ProfileViewModel)

    fun inject(repository: UserRepositoryImpl)
}