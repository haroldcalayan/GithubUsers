/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/28/2021
 */

package com.haroldcalayan.githubusers.ui.user

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haroldcalayan.githubusers.BR
import com.haroldcalayan.githubusers.R
import com.haroldcalayan.githubusers.base.BaseActivity
import com.haroldcalayan.githubusers.data.model.User
import com.haroldcalayan.githubusers.databinding.ActivityUserBinding
import com.haroldcalayan.githubusers.ui.profile.ProfileActivity
import kotlinx.android.synthetic.main.activity_user.*
import timber.log.Timber

class UserActivity : BaseActivity<ActivityUserBinding, UserViewModel>() {

    private lateinit var userAdapter: UserAdapter

    override fun getLayout() = R.layout.activity_user

    override fun getBindingVariable() = BR.viewModel

    override fun onResume() {
        super.onResume()
        getViewModel().getUsers()
    }

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
                openProfile(user)
            }
        }
        userAdapter = UserAdapter(listener, emptyList(), emptyList())
        recyclerview_user_users.adapter = userAdapter

        getViewModel().getCachedUsers()
        getViewModel().getUsers()
    }

    override fun subscribe() {
        super.subscribe()
        getViewModel().notes.observe(this, {
            getViewModel().users.value?.let { users ->
                userAdapter.updateList(users, it)
            }
        })
    }

    private fun openProfile(user: User) {
        Intent(this, ProfileActivity::class.java).apply {
            this.putExtra(ProfileActivity.EXTRA_USER_NAME, user.login)
            startActivity(this)
        }
    }
}