/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/29/2021
 */

package com.haroldcalayan.githubusers.util.media.image.picasso

import android.graphics.*
import com.squareup.picasso.Transformation

class CircleTransform : Transformation {

    override fun transform(source: Bitmap?): Bitmap {
        val size = Math.min(source?.width!!, source?.height!!)

        val x = (source?.width!! - size) / 2
        val y = (source?.height!! - size) / 2

        val squaredBitmap = source?.let { Bitmap.createBitmap(it, x, y, size, size) }
        if (squaredBitmap != source) {
            source!!.recycle()
        }

        val bitmap = Bitmap.createBitmap(size, size, source?.config!!)

        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader = squaredBitmap?.let { BitmapShader(it, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP) }
        paint.shader = shader
        paint.isAntiAlias = true

        val r = size / 2f
        canvas.drawCircle(r, r, r, paint)

        squaredBitmap?.recycle()
        return bitmap
    }

    override fun key(): String {
        return "circle";
    }
}
