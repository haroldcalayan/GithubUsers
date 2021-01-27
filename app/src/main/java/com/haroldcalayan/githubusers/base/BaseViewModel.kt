/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/27/2021
 */

package com.haroldcalayan.githubusers.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    protected val _loading = MutableLiveData<Boolean>()
    protected val _errorMessage = MutableLiveData<Int>()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
        }
    }

    /*
    LiveData Getters
     */
    val loading: LiveData<Boolean>
        get() = _loading
    val errorMessage: LiveData<Int>
        get() = _errorMessage
}