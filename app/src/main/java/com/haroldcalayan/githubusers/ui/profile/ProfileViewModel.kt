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
import com.haroldcalayan.githubusers.data.model.User
import com.haroldcalayan.githubusers.data.repository.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel : BaseViewModel() {

    @Inject
    lateinit var userRepository: UserRepository

    private val _user = MutableLiveData<User>()

    fun getUser(name: String) {
        viewModelScope.launch {
            _user.value = userRepository.getProfile(name)
        }
    }

    /*
    LiveData Getters
     */
    val user: LiveData<User>
        get() = _user
}