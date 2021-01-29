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
import com.haroldcalayan.githubusers.data.model.User
import com.haroldcalayan.githubusers.data.repository.user.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel : BaseViewModel() {

    @Inject
    lateinit var userRepository: UserRepository

    private val _users = MutableLiveData<List<User>>()

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            _users.value = userRepository.getAllUsers()
        }
    }

    fun getUsers(limit: Int, offset: Int) {

    }

    fun getCachedUsers() {
        viewModelScope.launch {
            _users.value = userRepository.getCachedUsers()
        }
    }

    /*
    LiveData Getters
     */
    val users: LiveData<List<User>>
        get() = _users
}