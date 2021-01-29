/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/29/2021
 */

package com.haroldcalayan.githubusers.util.extension

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.haroldcalayan.githubusers.util.media.image.picasso.CircleTransform
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String, fit: Boolean? = false) {
    val requestCreator = Picasso.get()
        .load(url)
    if(fit == true) requestCreator.fit()
    requestCreator.into(this)
}

fun ImageView.loadImage(url: String, @DrawableRes placeholder: Int, @DrawableRes error: Int, fit: Boolean? = false) {
    val requestCreator = Picasso.get()
        .load(url)
        .placeholder(placeholder)
        .error(error)
    if(fit == true) requestCreator.fit()
    requestCreator.into(this)
}

fun ImageView.loadCircleImage(url: String, @DrawableRes placeholder: Int, @DrawableRes error: Int, fit: Boolean? = false) {
    val requestCreator = Picasso.get()
        .load(url)
        .placeholder(placeholder)
        .error(error)
    requestCreator.transform(CircleTransform())
    if(fit == true) requestCreator.fit()
    requestCreator.into(this)
}

fun ImageView.loadImage(@DrawableRes drawable: Int, fit: Boolean? = false) {
    val requestCreator = Picasso.get()
        .load(drawable)
    if(fit == true) requestCreator.fit()
    requestCreator.into(this)
}

fun ImageView.loadImage(@DrawableRes drawable: Int, @DrawableRes placeholder: Int, @DrawableRes error: Int, fit: Boolean? = false) {
    val requestCreator = Picasso.get()
        .load(drawable)
        .placeholder(placeholder)
        .error(error)
    if(fit == true) requestCreator.fit()
    requestCreator.into(this)
}
