/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/29/2021
 */

package com.haroldcalayan.githubusers.data.repository.user

import com.haroldcalayan.githubusers.GithubUsersApp
import com.haroldcalayan.githubusers.base.BaseRepository
import com.haroldcalayan.githubusers.data.model.User
import com.haroldcalayan.githubusers.data.source.local.GithubUsersDatabase
import com.haroldcalayan.githubusers.data.source.remote.ApiClient
import javax.inject.Inject

class UserRepositoryImpl(private val appDatabase: GithubUsersDatabase) : BaseRepository(),
    UserRepository {

    @Inject
    lateinit var api: ApiClient

    init {
        GithubUsersApp.instance.appComponent.inject(this)
    }

    /**
     * Get specific / subset of users in the API and database
     *
     * <p>This method should be used when requesting list of users.</p>
     *
     */
    override suspend fun getAllUsers(): List<User> {
        if (GithubUsersApp.instance.hasInternetConnection()) {
            try {
                var users = api.getService()?.getUsers(0)

                if (users?.isNotEmpty() == true) {
                    appDatabase.userDao().deleteAll()
                    appDatabase.userDao().insertUsers(users)
                } else {
                    users = appDatabase.userDao().getAllUsers()
                }
                return users
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return appDatabase.userDao().getAllUsers()
    }

    /**
     * Get specific / subset of users in the API and database
     *
     * <p>This method should be used when requesting list of users.</p>
     *
     * @param since An Integer representing the starting user id for the Github's user api
     * @param limit An intent representing the limit of the rows to query in the database
     * @param offset An intent representing the starting row id to query in the database
     */
    override suspend fun getSomeUsers(since: Int, limit: Int, offset: Int): List<User> {
        if (GithubUsersApp.instance.hasInternetConnection()) {
            try {
                var users = appDatabase.userDao().getSomeUsers(limit, offset)
                if (users.isNotEmpty()) return users

                var usersFromRemote = api.getService()?.getUsers(since)

                if (usersFromRemote!!.isNotEmpty()) {
                    appDatabase.userDao().deleteAll()
                    appDatabase.userDao().insertUsers(users)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return appDatabase.userDao().getSomeUsers(limit, offset)
    }

    /**
     * Get all users saved in the database
     *
     * <p>This method should be used when requesting all users from the database.</p>
     *
     */
    override suspend fun getCachedUsers() = appDatabase.userDao().getAllUsers()

    override suspend fun getUser(id: Int) = appDatabase.userDao().getUser(id)

    override suspend fun getProfile(name: String) : User {
        if (GithubUsersApp.instance.hasInternetConnection()) {
            try {
                var profileFromRemote = api.getService()?.getProfile(name)
                if(profileFromRemote != null) appDatabase.userDao().update(profileFromRemote)
                return profileFromRemote ?: appDatabase.userDao().getUserByName(name)
            } catch (e : Exception) {
                e.printStackTrace()
            }
        }
        return appDatabase.userDao().getUserByName(name)
    }

    override suspend fun insertUser(user: User) = appDatabase.userDao().insert(user)

    override suspend fun deleteUsers() = appDatabase.userDao().deleteAll()

    override suspend fun updateUser(user: User) {
        appDatabase.userDao().update(user)
    }

}
