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

    override suspend fun getUser(id: Int): List<User> = appDatabase.userDao().getUser(id)

    override suspend fun insertUser(user: User) = appDatabase.userDao().insert(user)

    override suspend fun deleteUsers() = appDatabase.userDao().deleteAll()

}
