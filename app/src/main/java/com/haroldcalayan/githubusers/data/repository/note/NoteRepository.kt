/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/29/2021
 */

package com.haroldcalayan.githubusers.data.repository.note

import com.haroldcalayan.githubusers.data.model.Note

interface NoteRepository {

    suspend fun getAllNotes(): List<Note>

    suspend fun getSomeNotes(since: Int, limit: Int, offset: Int): List<Note>

    suspend fun getCachedNotes(): List<Note>

    suspend fun getNote(userId: Int): Note

    suspend fun insertNote(note: Note)

    suspend fun deleteNotes()

    suspend fun updateNote(note: Note)
}