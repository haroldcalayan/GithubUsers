/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/27/2021
 */

package com.haroldcalayan.githubusers.data.repository

import com.haroldcalayan.githubusers.data.model.User

interface UserRepository {

    suspend fun getAllUsers(): List<User>

    suspend fun getSomeUsers(since: Int, limit: Int, offset: Int): List<User>

    suspend fun getCachedUsers(): List<User>

    suspend fun getUser(id: Int): List<User>

    suspend fun getProfile(name: String): User

    suspend fun insertUser(user: User)

    suspend fun deleteUsers()

}