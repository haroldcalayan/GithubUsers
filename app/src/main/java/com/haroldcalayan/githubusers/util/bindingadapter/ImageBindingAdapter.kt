/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/29/2021
 */

package com.haroldcalayan.githubusers.util.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.haroldcalayan.githubusers.R
import com.haroldcalayan.githubusers.util.extension.loadCircleImage
import com.haroldcalayan.githubusers.util.extension.loadImage

@BindingAdapter(value = ["imageUrl"], requireAll = false)
fun ImageView.loadImage(imageUrl: String) {
    loadImage(imageUrl)
}

@BindingAdapter(value = ["circleImageUrl"], requireAll = false)
fun ImageView.circleImage(imageUrl: String) {
    loadCircleImage(imageUrl, R.mipmap.ic_launcher, R.mipmap.ic_launcher)
}