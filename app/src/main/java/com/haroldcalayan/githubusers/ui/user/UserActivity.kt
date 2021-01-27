/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/28/2021
 */

package com.haroldcalayan.githubusers.ui.user

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haroldcalayan.githubusers.BR
import com.haroldcalayan.githubusers.R
import com.haroldcalayan.githubusers.base.BaseActivity
import com.haroldcalayan.githubusers.data.model.User
import com.haroldcalayan.githubusers.databinding.ActivityUserBinding
import kotlinx.android.synthetic.main.activity_user.*
import timber.log.Timber

class UserActivity : BaseActivity<ActivityUserBinding, UserViewModel>() {

    private lateinit var userAdapter: UserAdapter

    override fun getLayout() = R.layout.activity_user

    override fun getBindingVariable() = BR.viewModel

    override fun initViews() {
        super.initViews()
        val linearLayoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerview_user_users.layoutManager = linearLayoutManager
        recyclerview_user_users.setHasFixedSize(true)
    }

    override fun initData() {
        super.initData()
        val listener = object: UserAdapter.Listener {
            override fun onItemClick(user: User) {

            }
        }
        userAdapter = UserAdapter(listener, emptyList())
        recyclerview_user_users.adapter = userAdapter

        getViewModel().getCachedUsers()
        getViewModel().getUsers()
    }

    override fun subscribe() {
        super.subscribe()
        getViewModel().users.observe(this, {
            Timber.d("getViewModel().users: $it")
            userAdapter.updateList(it)
        })
    }
}