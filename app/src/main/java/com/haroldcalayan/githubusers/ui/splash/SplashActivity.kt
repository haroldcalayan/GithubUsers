/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/28/2021
 */

package com.haroldcalayan.githubusers.ui.splash

import android.content.Intent
import com.haroldcalayan.githubusers.BR
import com.haroldcalayan.githubusers.R
import com.haroldcalayan.githubusers.base.BaseActivity
import com.haroldcalayan.githubusers.databinding.ActivitySplashBinding
import com.haroldcalayan.githubusers.ui.user.UserActivity
import kotlinx.coroutines.*

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun getLayout() = R.layout.activity_splash

    override fun getBindingVariable() = BR.viewModel

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }

    override fun initViews() {
        super.initViews()
        hideSystemUI()
        startTimer()
    }

    private fun startTimer() {
        activityScope.launch {
            delay(SPLASH_SCREEN_LIFE)
            startMain()
            finish()
        }
    }

    private fun startMain() {
        Intent(this, UserActivity::class.java).apply {
            startActivity(this)
        }
    }

    companion object {
        private const val SPLASH_SCREEN_LIFE = 3000L
    }
}