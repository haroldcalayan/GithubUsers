/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/29/2021
 */

package com.haroldcalayan.githubusers.di.module

import com.haroldcalayan.githubusers.util.media.image.ImageLoader
import com.haroldcalayan.githubusers.util.media.image.PicassoImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MediaModule {

    @Provides
    @Singleton
    fun provideImageLoader() : ImageLoader = PicassoImageLoader()
}