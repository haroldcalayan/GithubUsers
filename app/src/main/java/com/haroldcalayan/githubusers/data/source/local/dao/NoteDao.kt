/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/29/2021
 */

package com.haroldcalayan.githubusers.data.source.local.dao

import androidx.room.*
import com.haroldcalayan.githubusers.data.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    suspend fun getAllNotes(): List<Note>

    @Query("SELECT * FROM note LIMIT :limit OFFSET :offset")
    suspend fun getSomeNotes(limit: Int, offset: Int): List<Note>

    @Query("SELECT * FROM note WHERE userId = :userId")
    suspend fun getNote(userId: Int): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(users: List<Note>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("DELETE FROM note")
    suspend fun deleteAll()
}