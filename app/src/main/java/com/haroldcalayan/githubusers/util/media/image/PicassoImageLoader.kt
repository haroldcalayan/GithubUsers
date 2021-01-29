/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/29/2021
 */

package com.haroldcalayan.githubusers.util.media.image

import android.widget.ImageView
import com.squareup.picasso.Picasso

class PicassoImageLoader : ImageLoader {

    var picasso: Picasso = Picasso.get()

    override fun loadImage(imageView: ImageView, url: String) {
        picasso
            .load(url)
            .into(imageView)
    }

    override fun loadImage(imageView: ImageView, url: String, placeHolder: Int, error: Int) {
        picasso
            .load(url)
            .placeholder(placeHolder)
            .error(placeHolder)
            .into(imageView)
    }

    override fun loadImage(imageView: ImageView, imageSourceId: Int) {
        picasso
            .load(imageSourceId)
            .into(imageView)
    }

    override fun loadImage(imageView: ImageView, imageSourceId: Int, placeHolder: Int, error: Int) {
        picasso
            .load(imageSourceId)
            .placeholder(placeHolder)
            .error(placeHolder)
            .into(imageView)
    }
}