/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/29/2021
 */

package com.haroldcalayan.githubusers.util.media.image

import android.widget.ImageView

interface ImageLoader {

    fun loadImage(imageView: ImageView, url: String)

    fun loadImage(imageView: ImageView, url: String, placeHolder: Int, error: Int)

    fun loadImage(imageView: ImageView, imageSourceId: Int)

    fun loadImage(imageView: ImageView, imageSourceId: Int, placeHolder: Int, error: Int)
}