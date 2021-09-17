package com.yogadarma.awesomeapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

fun ImageView.loadImageRounded(url: String) {
    Glide.with(this.context)
        .load(url)
        .transform(CenterCrop(), RoundedCorners(20))
        .into(this)
}