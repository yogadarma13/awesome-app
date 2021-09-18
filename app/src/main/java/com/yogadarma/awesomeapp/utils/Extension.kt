package com.yogadarma.awesomeapp.utils

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

fun ImageView.loadImageRounded(url: String) {
    Glide.with(this.context)
        .load(url)
        .transform(CenterCrop(), RoundedCorners(20))
        .into(this)
}

fun Fragment.showToast(message: String?) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}