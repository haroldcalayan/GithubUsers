/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/27/2021
 */

package com.haroldcalayan.githubusers.data.repository

import com.haroldcalayan.githubusers.GithubUsersApp
import com.haroldcalayan.githubusers.base.BaseRepository
import com.haroldcalayan.githubusers.data.model.User
import com.haroldcalayan.githubusers.data.source.local.GithubUsersDatabase
import com.haroldcalayan.githubusers.data.source.remote.ApiClient
import timber.log.Timber
import javax.inject.Inject

class UserRepositoryImpl(private val appDatabase: GithubUsersDatabase) : BaseRepository(), UserRepository {

    @Inject
    lateinit var api: ApiClient

    init {
        GithubUsersApp.instance.appComponent.inject(this)
    }

    override suspend fun getAllUsers(): List<User> {
        try {
            var users = api.getService()?.getUsers(0)

            if (users?.isNotEmpty() == true) {
                appDatabase.userDao().deleteAll()
                appDatabase.userDao().insertUsers(users)
            } else {
                users = appDatabase.userDao().getAllUsers()
            }
            return users
        } catch (e : Exception) {
            e.printStackTrace()
        }
        return appDatabase.userDao().getAllUsers()
    }

    override suspend fun getSomeUsers(page: Int, limit: Int, offset: Int): List<User> {
        try {
            var users = appDatabase.userDao().getSomeUsers(limit, offset)
            if(users.isNotEmpty()) return users

            var usersFromRemote = api.getService()?.getUsers(page)

            if(usersFromRemote!!.isNotEmpty()) {
                appDatabase.userDao().deleteAll()
                appDatabase.userDao().insertUsers(users)
            }
        } catch (e : Exception) {
            e.printStackTrace()
        }
        return appDatabase.userDao().getSomeUsers(limit, offset)
    }

    override suspend fun getCachedUsers() = appDatabase.userDao().getAllUsers()

    override suspend fun getUser(id: Int) = appDatabase.userDao().getUser(id)

    override suspend fun getProfile(name: String) : User {
        Timber.d("getProfile() name: $name")
        if (GithubUsersApp.instance.hasInternetConnection()) {
            Timber.d("enters if")
            try {
                Timber.d("enters try")
                var profileFromRemote = api.getService()?.getProfile(name)
                if(profileFromRemote != null) {
                    appDatabase.userDao().insert(profileFromRemote)
                }
                Timber.d("profileFromRemote: $profileFromRemote")
                return profileFromRemote ?: appDatabase.userDao().getUserByName(name)
            } catch (e : Exception) {
                Timber.d("enters catch")
                e.printStackTrace()
            }
            return appDatabase.userDao().getUserByName(name)
        } else {
            Timber.d("enters else")
            return appDatabase.userDao().getUserByName(name)
        }
    }

    override suspend fun insertUser(user: User) = appDatabase.userDao().insert(user)

    override suspend fun deleteUsers() = appDatabase.userDao().deleteAll()

}
