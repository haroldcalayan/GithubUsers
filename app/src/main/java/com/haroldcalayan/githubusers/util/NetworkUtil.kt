/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/27/2021
 */

package com.haroldcalayan.githubusers.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworkUtil {

    fun isNetConneted(context: Context) : Boolean {
        val connectManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo : NetworkInfo?= connectManager.activeNetworkInfo
        return if(networkInfo==null) false else networkInfo.isAvailable&& networkInfo.isConnected
    }

    fun isNetworkConnected(context: Context, typeMobile : Int) : Boolean {
        if(!isNetConneted(context)) return false
        val connectManager  = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo : NetworkInfo? = connectManager.getNetworkInfo(typeMobile)
        return if(networkInfo == null) false else networkInfo.isConnected && networkInfo.isAvailable
    }

    fun isPhoneNetConnected(context: Context) : Boolean {
        val typeMobile = ConnectivityManager.TYPE_MOBILE
        return isNetworkConnected(context,typeMobile)
    }

    fun isWifiNetConnected(context: Context) : Boolean{
        val typeMobile = ConnectivityManager.TYPE_WIFI
        return  isNetworkConnected(context,typeMobile)
    }
}