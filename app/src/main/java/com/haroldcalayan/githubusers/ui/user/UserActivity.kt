/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/28/2021
 */

package com.haroldcalayan.githubusers.ui.user

import com.haroldcalayan.githubusers.BR
import com.haroldcalayan.githubusers.R
import com.haroldcalayan.githubusers.base.BaseActivity
import com.haroldcalayan.githubusers.databinding.ActivityUserBinding

class UserActivity : BaseActivity<ActivityUserBinding, UserViewModel>() {

    override fun getLayout() = R.layout.activity_user

    override fun getBindingVariable() = BR.viewModel
}