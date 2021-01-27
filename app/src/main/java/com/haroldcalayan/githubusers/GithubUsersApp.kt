/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/27/2021
 */

package com.haroldcalayan.githubusers

import android.app.Application
import com.haroldcalayan.githubusers.di.component.AppComponent
import com.haroldcalayan.githubusers.di.component.DaggerAppComponent
import com.haroldcalayan.githubusers.di.module.AppModule
import com.haroldcalayan.githubusers.util.NetworkUtil
import timber.log.Timber

class GithubUsersApp : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initLog()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .appModule(AppModule(this, BuildConfig.DB_NAME))
            .build()
    }

    private fun initLog() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    fun hasInternetConnection() : Boolean {
        return NetworkUtil.isNetConneted(this)
    }

    companion object {
        lateinit var instance: GithubUsersApp
    }
}