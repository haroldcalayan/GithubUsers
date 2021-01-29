/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/27/2021
 */

package com.haroldcalayan.githubusers.data.source.local.dao

import androidx.room.*
import com.haroldcalayan.githubusers.data.model.User


@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM user LIMIT :limit OFFSET :offset")
    suspend fun getSomeUsers(limit: Int, offset: Int): List<User>

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUser(id: Int): List<User>

    @Query("SELECT * FROM user WHERE login = :name")
    suspend fun getUserByName(name: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<User>)

    @Update
    suspend fun update(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteAll()

}
