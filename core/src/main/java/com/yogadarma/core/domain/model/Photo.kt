package com.yogadarma.core.domain.model

data class Photo(
    val id: Int,
    val portraitPhotoUrl: String,
    val landscapePhotoUrl: String,
    val photographer: String
)