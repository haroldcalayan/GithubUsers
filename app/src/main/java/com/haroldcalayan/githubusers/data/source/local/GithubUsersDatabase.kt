/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/27/2021
 */

package com.haroldcalayan.githubusers.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.haroldcalayan.githubusers.data.model.User
import com.haroldcalayan.githubusers.data.source.local.dao.UserDao

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class GithubUsersDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao
}