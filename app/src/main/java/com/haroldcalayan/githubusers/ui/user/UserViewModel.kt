/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/28/2021
 */

package com.haroldcalayan.githubusers.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haroldcalayan.githubusers.base.BaseViewModel
import com.haroldcalayan.githubusers.data.model.Note
import com.haroldcalayan.githubusers.data.model.User
import com.haroldcalayan.githubusers.data.repository.note.NoteRepository
import com.haroldcalayan.githubusers.data.repository.user.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel : BaseViewModel() {

    @Inject
    lateinit var userRepository: UserRepository
    @Inject
    lateinit var noteRepository: NoteRepository

    private val _users = MutableLiveData<List<User>>()
    private val _notes = MutableLiveData<List<Note>>()

    fun getUsers() {
        viewModelScope.launch {
            _users.value = userRepository.getAllUsers()
            getNotes()
        }
    }

    fun getUsers(limit: Int, offset: Int) {

    }

    fun getCachedUsers() {
        viewModelScope.launch {
            _users.value = userRepository.getCachedUsers()
        }
    }

    fun getNotes() {
        viewModelScope.launch {
            _notes.value = noteRepository.getAllNotes()
        }
    }

    /*
    LiveData Getters
     */
    val users: LiveData<List<User>>
        get() = _users
    val notes: LiveData<List<Note>>
        get() = _notes
}