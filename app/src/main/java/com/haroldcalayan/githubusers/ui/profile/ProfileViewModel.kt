/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/28/2021
 */

package com.haroldcalayan.githubusers.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haroldcalayan.githubusers.base.BaseViewModel
import com.haroldcalayan.githubusers.data.model.Note
import com.haroldcalayan.githubusers.data.model.User
import com.haroldcalayan.githubusers.data.repository.note.NoteRepository
import com.haroldcalayan.githubusers.data.repository.user.UserRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ProfileViewModel : BaseViewModel() {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var noteRepository: NoteRepository

    private val _user = MutableLiveData<User>()
    private val _note = MutableLiveData<Note>()

    fun getUser(name: String) {
        viewModelScope.launch {
            _user.value = userRepository.getProfile(name)
            _user?.value?.id?.let { getNote(it) }
        }
    }

    fun getNote(userId: Int) {
        viewModelScope.launch {
            _note.value = noteRepository.getNote(userId)
        }
    }

    fun updateNotes(notes: String) {
        viewModelScope.launch {
            val updatedNote = _user.value?.id?.let { Note(it, notes) }
            if (updatedNote != null) noteRepository.updateNote(updatedNote)
        }
    }

    /*
    LiveData Getters
     */
    val user: LiveData<User>
        get() = _user
    val note: LiveData<Note>
        get() = _note
}