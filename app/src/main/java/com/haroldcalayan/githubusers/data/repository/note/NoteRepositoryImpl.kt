/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/29/2021
 */

package com.haroldcalayan.githubusers.data.repository.note

import com.haroldcalayan.githubusers.data.model.Note
import com.haroldcalayan.githubusers.data.source.local.GithubUsersDatabase

class NoteRepositoryImpl(private val appDatabase: GithubUsersDatabase) : NoteRepository {

    override suspend fun getAllNotes() = appDatabase.noteDao().getAllNotes()

    override suspend fun getSomeNotes(since: Int, limit: Int, offset: Int): List<Note> {
        return appDatabase.noteDao().getSomeNotes(limit, offset)
    }

    override suspend fun getCachedNotes() = appDatabase.noteDao().getAllNotes()

    override suspend fun getNote(userId: Int) = appDatabase.noteDao().getNote(userId)

    override suspend fun insertNote(note: Note) {
        appDatabase.noteDao().insert(note)
    }

    override suspend fun deleteNotes() {
        appDatabase.noteDao().deleteAll()
    }

    override suspend fun updateNote(note: Note) {
        val savedNote = appDatabase.noteDao().getNote(note.userId)
        if(savedNote == null) {
            appDatabase.noteDao().insert(note)
        } else {
            appDatabase.noteDao().update(note)
        }
    }
}