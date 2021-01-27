/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/28/2021
 */

package com.haroldcalayan.githubusers.ui.profile

import androidx.lifecycle.Observer
import com.haroldcalayan.githubusers.BR
import com.haroldcalayan.githubusers.R
import com.haroldcalayan.githubusers.base.BaseActivity
import com.haroldcalayan.githubusers.data.model.User
import com.haroldcalayan.githubusers.databinding.ActivityProfileBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : BaseActivity<ActivityProfileBinding, ProfileViewModel>() {

    override fun getLayout() = R.layout.activity_profile

    override fun getBindingVariable() = BR.viewModel

    override fun initData() {
        super.initData()
        val name = intent.extras?.getString(EXTRA_USER_NAME) ?: ""
        getViewModel().getUser(name)
    }

    override fun subscribe() {
        super.subscribe()
        getViewModel().user.observe(this, {
            populateData(it)
        })
    }

    private fun populateData(user: User) {
        Picasso.get()
            .load(user.avatarUrl)
            .fit()
            .into(imageview_profile_image)

        textview_profile_followers.text = "followers: ".plus(user.followers)
        textview_profile_following.text = "following: ".plus(user.following)

        val information = StringBuilder().apply {
            append("name: ")
            append(user.name)
            append("\n")
            append("compane: ")
            append(user.company)
            append("\n")
            append("blog: ")
            append(user.blog)
            append("\n")
            append("location: ")
            append(user.location)
            append("\n")
            append("node id: ")
            append(user.nodeId)
            append("\n")
            append("public gists: ")
            append(user.publicGists)
            append("\n")
            append("public repos: ")
            append(user.publicRepos)
            append("\n")
            append("twitter username: ")
            append(user.twitterUsername)
            append("\n")
        }
        textview_user_information.text = information.toString()
    }

    companion object {
        const val EXTRA_USER_NAME = "user_name"
    }
}